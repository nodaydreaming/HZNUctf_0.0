package com.ctfplatform.demo.web;

import com.ctfplatform.demo.dao.ProblemsDao;
import com.ctfplatform.demo.entity.*;
import com.ctfplatform.demo.entity.send.*;
import com.ctfplatform.demo.service.*;
import com.mchange.util.Base64Encoder;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 通过socket通信将比赛数据发送到比赛端系统
 * 发送有5种内容：
 * 比赛信息
 * 选手信息
 * 队伍信息
 * 题目信息，题目资源采用读取二进制流的方式保存成字符串，进行传递
 * 管理员信息
 * 接收端信息{ip: (目前)172.22.227.88, port: 13301, username: 123456, password: 123456}
 */
@RestController
@RequestMapping("/send")
public class SendDataController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompetitionQuestionService competitionQuestionService;
    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private CompetitionUserService competitionUserService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestiontypeService questiontypeService;
    @Autowired
    private TeamCompetitorService teamCompetitorService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionAdminService competitionAdminService;
    @Autowired
    private ProblemsDao problemsDao;
//
//    private static final String IP_ADDR = "172.22.227.99";
//    private static final int PORT = 13301;

    //获得比赛信息
    public List<SendCompetition> getCompetitons(int competitonId){
        List<SendCompetition> list = new LinkedList<>();
        //获得到所有比赛
        List<Competition> competitions = competitionService.queryCompetition();
        //得到id相等比赛
        for (Competition c : competitions) {
            if(c.getCompetitionId().equals(competitonId)){
                SendCompetition sendCompetition = new SendCompetition();

                sendCompetition.setId(c.getCompetitionId());
                sendCompetition.setCompetitionId(c.getCompetitionNumber());
                sendCompetition.setStartTime(c.getCompetitionStart().getTime());
                sendCompetition.setEndTime(c.getCompetitionEnd().getTime());
                sendCompetition.setName(c.getCompetitionTitle());
                sendCompetition.setOrganizer(c.getCompetitionHolder());
                sendCompetition.setCanRegister(false);
                if(c.getCompetitionIsteam() == 1){
                    sendCompetition.setTeamCompetition(true);
                }else{
                    sendCompetition.setTeamCompetition(false);
                }
                list.add(sendCompetition);
            }
        }
        return list;
    }
    //获得选手信息
    public List<SendCompetitor> getCompetitors(int competitonId){
        List<SendCompetitor> list = new LinkedList<>();
        String competitionNumber = "";
        //选手报名与比赛对应的表查询
        List<CompetitionUser> competitionUsers = competitionUserService.listCompetitionUser();
        List<User> users = userService.queryUser();
        //队伍和选手对应表
        List<TeamCompetitor> teamCompetitors = teamCompetitorService.queryTeamCompetitor();
        //获得到所有比赛
        List<Competition> competitions = competitionService.queryCompetition();
        List<Team> teams = teamService.queryTeam();
        //得到id相等比赛的编号
        for (Competition c: competitions) {
            if (c.getCompetitionId().equals(competitonId)) {
                competitionNumber = c.getCompetitionNumber();
                break;
            }
        }
        for(Team t : teams){
            if(t.getTeamCompetitionId() == competitonId){
                int teamId = t.getTeamId();
                for(TeamCompetitor teamCompetitor : teamCompetitors){
                    if(teamCompetitor.getTeamId() == teamId){
                        for(User u : users){
                            if(u.getCompetitorId().equals(teamCompetitor.getCompetitorId())){
                                SendCompetitor sendCompetitor = new SendCompetitor();
                                sendCompetitor.setAccount(u.getCompetitorUsername());
                                sendCompetitor.setBanned(false);
                                sendCompetitor.setCompetitionId(competitionNumber);
                                sendCompetitor.setId(u.getCompetitorId());
                                sendCompetitor.setPassword(userService.decrypt(u.getCompetitorPassword()));
                                sendCompetitor.setName(u.getCompetitorNickname());
                                sendCompetitor.setTeamAccount(t.getTeamId()+"");

                                list.add(sendCompetitor);
                            }
                        }
                    }
                }
            }
        }
//        for (CompetitionUser competitionUser : competitionUsers) {
//            if (competitionUser.getCompetitionId() == competitonId) {
//                //得到用户id并设id
//                int competitorId = competitionUser.getCompetitorId();
//                SendCompetitor sendCompetitor = new SendCompetitor();
//                sendCompetitor.setId(competitorId);
//                sendCompetitor.setCompetitionId(competitionNumber);
//                for (User u : users) {
//                    //得到用户信息
//                    if (u.getCompetitorId() == competitorId) {
//                        //设置账号、密码、昵称、禁赛状态
//                        sendCompetitor.setAccount(u.getCompetitorUsername());
//                        sendCompetitor.setPassword(userService.decrypt(u.getCompetitorPassword()));
//                        sendCompetitor.setName(u.getCompetitorNickname());
//                        sendCompetitor.setBanned(false);
//                    }
//                }
//                for (TeamCompetitor teamCompetitor : teamCompetitors) {
//                    //得到用户所在队伍的id并设置为返回选手信息中的队伍账号
//                    if (teamCompetitor.getCompetitorId() == competitorId) {
//                        sendCompetitor.setTeamAccount(teamCompetitor.getTeamId() + "");
//                    }
//                }
//                list.add(sendCompetitor);
//            }
//        }

        return list;
    }
    //获得队伍信息
    public List<SendTeam> getTeams(int competitonId){
        List<SendTeam> list = new LinkedList<>();
        String competitionNumber = "";

        //队伍和选手对应表
        List<TeamCompetitor> teamCompetitors = teamCompetitorService.queryTeamCompetitor();
        //队伍表
        List<Team> teams = teamService.queryTeam();
        //获得到所有的用户
        List<User> users = userService.queryUser();
        //获得到所有比赛
        List<Competition> competitions = competitionService.queryCompetition();
        //得到id相等比赛的编号
        for (Competition c: competitions) {
            if (c.getCompetitionId().equals(competitonId)) {
                competitionNumber = c.getCompetitionNumber();
            }
        }
        for (Team t : teams) {
            if (t.getTeamCompetitionId() == competitonId) {
                SendTeam sendTeam = new SendTeam();
                //得到队伍的id，设置为sendTeam的id和account
                int teamId = t.getTeamId();
                sendTeam.setId(teamId);
                sendTeam.setAccount(teamId+"");
                sendTeam.setCompetitionId(competitionNumber);
                sendTeam.setName(t.getTeamName());
                List<String> teamMates = new LinkedList<>();
                for (TeamCompetitor teamCompetitor : teamCompetitors) {
                    if(teamCompetitor.getTeamId() == teamId){
                        for (User u : users) {
//                            System.out.println(u.toString());
                            if(u.getCompetitorId().equals(teamCompetitor.getCompetitorId())){
                                teamMates.add(u.getCompetitorUsername());
                                if(teamCompetitor.getCompetitorLevel() == 1){
                                    sendTeam.setCaptain(u.getCompetitorUsername());
                                }
                            }

                        }
                    }

                }
                //设置队伍队员账号数组
                sendTeam.setTeamMates(teamMates);

                //数据封装完添加到数组
                list.add(sendTeam);
            }
        }
        return list;
    }
    //获得题目信息
    public List<SendProblem> getProblems(int competitonId, HttpServletRequest request){
        //获得已经发送过的题目数组
        List<Problems> sendedProblems = problemsDao.queryByCompetitionId(competitonId);
        //获得该比赛题目列表的最大题目序号
        int order = 0;
        if(sendedProblems != null){
            for(Problems p : sendedProblems){
                if(p.getProblemOrder() > order){
                    order = p.getProblemOrder();
                }
            }
        }
        //获得要发送的题目信息
        List<SendProblem> list = new LinkedList<>();
        String competitionNumber = "";
        List<CompetitionQuestion> competitionQuestions = competitionQuestionService.queryCompetitionquestion();
        List<Question> questions = questionService.queryQuestion();
        //题目类型
        List<Questiontype> questiontypes = questiontypeService.queryQuestiontype();
        //获得到所有比赛
        List<Competition> competitions = competitionService.queryCompetition();
        //得到id相等比赛的编号
        for (Competition c: competitions) {
            if (c.getCompetitionId().equals(competitonId)) {
                competitionNumber = c.getCompetitionNumber();
            }
        }
        for (CompetitionQuestion cq : competitionQuestions) {
            if(cq.getCompetitionId() == competitonId) {
                for (Question q : questions) {
//                    System.out.println(cq.getQuestionId());
//                    System.out.println(q.getQuestionId());
                    if (cq.getQuestionId().equals(q.getQuestionId())) {
//                        System.out.println("in"+q.getQuestionId());
                        SendProblem sendProblem = new SendProblem();

                        sendProblem.setCompetitionId(competitionNumber);
                        sendProblem.setId(q.getQuestionId());
                        sendProblem.setAuthor(q.getQuestionAuthor());
                        sendProblem.setTitle(q.getQuestionTitle());
                        sendProblem.setDescription(q.getQuestionBody());
                        sendProblem.setBaseScore(q.getQuestionPoint());
                        sendProblem.setExtraScore(q.getQuestionAdditional());
                        sendProblem.setFullScore(q.getQuestionPoint() + q.getQuestionAdditional());
                        sendProblem.setScoreStep(q.getQuestionDecrease());
                        sendProblem.setAnswer(q.getQuestionAnswer());
                        sendProblem.setProblemId(cq.getQuestionNumber());
                        sendProblem.setLevel(q.getQuestionLevel() == null ? 0 : q.getQuestionLevel());
                        sendProblem.setResourceURL(new SendDataController().getBytes(q.getQuestionResource(), request));
//                        System.out.println("In send " + sendProblem.getResourceURL());
                        for (Questiontype qt : questiontypes) {
//                            System.out.println(qt.toString());
                            if (q.getQuestionTypeId().equals( qt.getQuestionTypeId())) {
                                sendProblem.setProblemType(qt.getQuestionType());
                            }
                        }
//                    sendProblem.setProblemId();
//                        System.out.println(sendProblem.toString());
                        list.add(sendProblem);
                    }
                }
            }
        }
        List<SendProblem> resultList = new LinkedList<>();
        for(int i = 0; i < list.size(); ++i){
            for(int j = 0; j < sendedProblems.size(); ++j){
                Problems sended = sendedProblems.get(j);
                if(sended.getProblemId() == list.get(i).getId()){
                    break;
                }
                else{
                    SendProblem sp = list.get(i);
                    sp.setProblemId(++order);
                    resultList.add(sp);
                    Problems p = new Problems();
                    p.setCompetitionId(competitonId);
                    p.setProblemId(sp.getId());
                    p.setProblemOrder(sp.getProblemId());
                    problemsDao.insert(p);
                }
            }

        }
        return resultList;
    }
    //获得管理员信息
    public List<SendAdmin> getAdmins(int competitonId){
        List<SendAdmin> list = new LinkedList<>();
        List<CompetitionAdmin> competitionAdmins = competitionAdminService.list();
        List<Admin> admins = adminService.queryAdmin();
        List<Competition> competitions = competitionService.queryCompetition();
        String competitionNumber = "";
        //得到id相等比赛的编号
        for (Competition c: competitions) {
            if (c.getCompetitionId().equals(competitonId)) {
                competitionNumber = c.getCompetitionNumber();
                break;
            }
        }
        for(CompetitionAdmin ca : competitionAdmins){
            if(ca.getCompetitionId() == competitonId){
                for(Admin a : admins){
                    if(a.getAdminId() == ca.getAdminId()){
                        SendAdmin sendAdmin = new SendAdmin();
                        sendAdmin.setAccount(a.getAdminUsername());
                        sendAdmin.setBanned(false);
                        sendAdmin.setCompetitionId(competitionNumber);
                        sendAdmin.setId(a.getAdminId());
                        sendAdmin.setName(a.getAdminNickname());
                        sendAdmin.setPassword(userService.decrypt(a.getAdminPassword()));
                        list.add(sendAdmin);
                    }
                }
            }
        }
        return list;
    }

    public String getBytes(String file, HttpServletRequest request){
//        System.out.println(file);
        String filePath = request.getSession().getServletContext().getRealPath("/upload/problemResource");
        String filename = file.substring(file.lastIndexOf('/')+1);
        String path = filePath + File.separator + filename;
//        System.out.println("filePath" + filePath);
//        System.out.println("fileName" + filename);
//        System.out.println("path" + path);
        File resource = new File(path);
//        System.out.println("file" + resource);
        try  {
            FileInputStream fileInputStream = new FileInputStream(resource);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            byte[] bytes = new byte[(int) resource.length()];
            dataInputStream.read(bytes);
//            System.out.println(bytes);

            String str = Base64.encodeBase64String(bytes);
//            System.out.println(str);
            while(str.charAt(str.length() - 1) == '='){
                String substr = str.substring(0, str.length()-1);
//                System.out.println(substr);
                str = substr;
            }
//            System.out.println("In function" + str);
            return str;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping(value = "/sendData", method = RequestMethod.POST)
    public Map<String, Object> sendDataToCompetiton(int competitionId, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        //获取5种数据
        List<SendCompetition> competitions = getCompetitons(competitionId);
        List<SendCompetitor> competitors = getCompetitors(competitionId);
        List<SendTeam> teams = getTeams(competitionId);
        List<SendProblem> problems = getProblems(competitionId, request);
        List<SendAdmin> admins = getAdmins(competitionId);

        resultMap.put("competitions", competitions);
        resultMap.put("competitors", competitors);
        resultMap.put("teams", teams);
        resultMap.put("admins", admins);
        resultMap.put("problems", problems);

        return resultMap;
    }

    @RequestMapping(value = "getRegistration", method = RequestMethod.GET)
    private Map<String, Object> getRegistration(int competitionId, int page, int limit){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<RegistrationEntity> list = new LinkedList<>();
        List<Team> teams = teamService.queryTeam();
        int num=0;
        for(Team t : teams){
            if(t.getTeamCompetitionId() == competitionId){
                List<TeamCompetitor> usersId = teamCompetitorService.queryCompetitorIdByTeamId(t.getTeamId());
                for(TeamCompetitor i : usersId){
                    CompetitionUser competitionUser = competitionUserService.getCompeititonUserByUserIdAndCompetitionId(i.getCompetitorId(), competitionId);
                    User u = userService.queryUserById(i.getCompetitorId());
                    RegistrationEntity r = new RegistrationEntity();
                    r.setId(++num);
                    r.setUsername(u.getCompetitorUsername());
                    r.setName(u.getCompetitorName());
                    r.setGender(u.getCompetitorGender());
                    r.setCollege(u.getCompetitorAcademy());
                    r.setProfessional(u.getCompetitorClass());
                    r.setNumber(u.getCompetitorStudentnumber());
                    r.setDate(sdf.format(competitionUser.getEnteringTime()));
                    r.setTeamName(t.getTeamName());

                    list.add(r);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg","");
        map.put("count", list.size());

        int startNum = (page - 1) * limit;
        int endNum = page * limit;
        List<RegistrationEntity> resultList = new LinkedList<>();
        for(int i = startNum; i < list.size() && i < endNum; ++i){
            resultList.add(list.get(i));
        }
        map.put("data", resultList);
        return map;
    }

    /**
     *
     * @param number
     * @param request
     * @return resultMap
     * 若用户没有报名，返回status = 0；
     * 已报名返回 status = 1，并且返回队伍名，队长姓名，队员姓名
     */
    @RequestMapping(value = "getUserStatus", method = RequestMethod.POST)
    private Map<String, Object> getUserStatus(String number, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        Competition competition = competitionService.queryCompetitionByNumber(number);
        User loginUser = (User)request.getSession().getAttribute("user");
        if(loginUser != null) {
            try {
                CompetitionUser competitionUser = competitionUserService.getCompeititonUserByUserIdAndCompetitionId(loginUser.getCompetitorId(), competition.getCompetitionId());
                if (competitionUser != null) {
                    List<String> memberNames = new LinkedList<>();
                    String captainName = "";
                    List<User> users = userService.queryUser();
                    List<TeamCompetitor> teamCompetitors = teamCompetitorService.queryTeamCompetitor();
                    List<Team> teams = teamService.queryTeam();
                    Team team = null;
                    //得到用户所在的队伍
                    for (Team t : teams) {
                        if (t.getTeamCompetitionId() == competition.getCompetitionId().intValue()) {
                            for (TeamCompetitor tc : teamCompetitors) {
                                System.out.print("tc.getCompetitorId():"+tc.getCompetitorId()+"\t");
                                System.out.println("loginUser.getCompetitorId()"+loginUser.getCompetitorId());
                                System.out.print("tc.getTeamId()"+tc.getTeamId()+"\t");
                                System.out.println("t.getTeamId()"+t.getTeamId());
                                if (tc.getCompetitorId() == loginUser.getCompetitorId().intValue()
                                        && tc.getTeamId() == t.getTeamId().intValue()) {
                                    System.out.println(t);
                                    team = t;
                                    break;
                                }
                            }
                        }
                    }
                    if (team != null) {
                        for (TeamCompetitor tc : teamCompetitors) {
                            if (tc.getTeamId() == team.getTeamId().intValue()) {
                                for (User u : users) {
                                    if (u.getCompetitorId() == tc.getCompetitorId().intValue()) {
                                        memberNames.add(u.getCompetitorName());
                                        if (tc.getCompetitorLevel() == 1) {
                                            captainName = u.getCompetitorName();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    resultMap.put("teamName", team == null ? null : team.getTeamName());
                    resultMap.put("status", 1);
                    resultMap.put("members", memberNames);
                    resultMap.put("captainName", captainName);
                } else {
                    resultMap.put("status", 0);
                }
            }catch (Exception e){
                resultMap.put("status", 0);
            }
        }
        else {
            resultMap.put("status", 0);
        }
        return resultMap;
    }

    public static void main(String[] args){
        String filePath = "D:\\apache-tomcat-9.0.13\\webapps\\hznuctf\\upload\\problemResource\\201904122326194047552016210402076郭亚杰.zip";
        File resource = new File(filePath);
        try  {
            FileInputStream fileInputStream = new FileInputStream(resource);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            byte[] bytes = new byte[(int) resource.length()];
            dataInputStream.read(bytes);
//            System.out.println(bytes);

            String str = Base64.encodeBase64String(bytes);
//            System.out.println(str);
            while(str.charAt(str.length() - 1) == '='){
                String substr = str.substring(0, str.length()-1);
//                System.out.println(substr);
                str = substr;
            }
            System.out.println(str);
        } catch (IOException e){
//            return null;
            e.printStackTrace();
        }
//        Integer a = new Integer(5);
//        Integer b = new Integer(5);
//        System.out.println(a == b);
//        System.out.println(a.equals(b));

    }



}
