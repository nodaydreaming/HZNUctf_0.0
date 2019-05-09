package com.ctfplatform.demo.web;

import com.ctfplatform.demo.config.service.RSA;
import com.ctfplatform.demo.dao.TeamDao;
import com.ctfplatform.demo.entity.CompetitionUser;
import com.ctfplatform.demo.entity.Team;
import com.ctfplatform.demo.entity.TeamCompetitor;
import com.ctfplatform.demo.entity.User;
import com.ctfplatform.demo.service.CompetitionUserService;
import com.ctfplatform.demo.service.TeamCompetitorService;
import com.ctfplatform.demo.service.TeamService;
import com.ctfplatform.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * 根据模拟赛排名，筛选队伍进入决赛
 * 通过晋级的队伍id，把队伍和队员与正式比赛创建联系
 * 0、根据输入的队伍id，找出晋级的队伍（输入的队伍id是以空格为间隔的字符串）
 * 1、创建属于CTF决赛的队伍名与模拟赛相同的队伍；
 * 2、将原来队员与新队伍创建联系
 * 3、将队员与新比赛创建联系
 */
@RestController
@RequestMapping("/changeData")
public class ChangeData {
    public static final String competitionNumber = "HZNU_CTF_01";
    public static final int competitionId = 47;

    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamCompetitorService teamCompetitorService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompetitionUserService competitionUserService;
    //根据队伍id进行上述操作
    public static void updateData(CompetitionUserService competitionUserService, UserService userService, TeamService teamService, TeamCompetitorService teamCompetitorService, List<Team> teamList, int teamId){
        //得到这支队伍的队员信息
        List<TeamCompetitor> teamCompetitorList = teamCompetitorService.queryCompetitorIdByTeamId(teamId);

        List<User> teamMates = new LinkedList<>();
        for(int i = 0; i < teamCompetitorList.size(); ++i){
            teamMates.add(userService.queryUserById(teamCompetitorList.get(i).getCompetitorId()));
        }
        //获得队伍名
        String teamName = null;
        for(int i = 0; i < teamList.size(); ++i){
            if(teamList.get(i).getTeamId().equals(teamId)){
                teamName = teamList.get(i).getTeamName();
                break;
            }
        }
        if(teamName != null && teamMates.size() != 0){
            //创建新队伍
            Team t = new Team();
            t.setTeamName(teamName);
            t.setTeamCompetitionId(competitionId);
            teamService.insertTeam(t, teamMates.get(0).getCompetitorUsername());
            Map<String, Object> insertTeamMap = teamService.queryTeamByNameAndCompetitionId(teamName, competitionId);
            if(insertTeamMap.containsKey("team")){
                Team newTeam = (Team) insertTeamMap.get("team");
                //创建队伍与队员的联系
                for (int k = 1; k < teamMates.size(); ++k){
                    TeamCompetitor tc = new TeamCompetitor();
                    tc.setTeamId(newTeam.getTeamId());
                    tc.setCompetitorId(teamMates.get(k).getCompetitorId());
                    tc.setCompetitorLevel(0);
                    teamCompetitorService.insertTeamCompetitor(tc);
                }
                //创建选手和比赛的联系
                for(int j = 0; j < teamMates.size(); ++j){
                    CompetitionUser competitionUser = new CompetitionUser();
                    competitionUser.setCompetitionId(competitionId);
                    competitionUser.setCompetitorId(teamMates.get(j).getCompetitorId());
                    competitionUser.setEnteringTime(new Date());

                    competitionUserService.insertCompetitionUser(competitionUser);
                }
            }

        }
    }
    @RequestMapping(value = "change", method = RequestMethod.POST)
    private Map<String,Object> change(HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<>();
        String teamIdStr;
        List<Team> teamList = teamService.queryTeam();
        teamIdStr = request.getParameter("teams");
        System.out.println(teamIdStr);
        if(teamIdStr != null && teamIdStr != "") {
            String[] teamIds = teamIdStr.split(" ");
            //以队伍为单位进行处理
            for(int i = 0; i < teamIds.length; ++i){
                int teamId = Integer.parseInt(teamIds[i]);
                updateData(competitionUserService, userService, teamService, teamCompetitorService, teamList, teamId);
            }
        }
        return resultMap;
    }
    //主函数
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("请选择1、加密；2、解密");
            int choosen = Integer.parseInt(in.nextLine());
            if(choosen == 1){
                RSA rsa = new RSA();
                System.out.println("请输入原文：");

                String original = in.nextLine();
                String ciphertext = null;
                try {
                    ciphertext = rsa.testEncrypt(rsa.privateKey, original);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    e.printStackTrace();
                }
                System.out.println(ciphertext);
            }
            else{
                RSA rsa = new RSA();
                System.out.println("请输入密文：");
                String ciphertext = in.nextLine();
                String original = null;
                try {
                    original = rsa.testDecrypt(rsa.publicKey, ciphertext);
                } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
                        | IllegalBlockSizeException | BadPaddingException | IOException e) {
                    e.printStackTrace();
                }
                System.out.println(original);
            }
        }
    }
}
