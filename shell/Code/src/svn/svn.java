/**
 * Project Name:Code
 * File Name:svn.java
 * Package Name:svn
 * Date:2019年2月21日上午9:08:32
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package svn;
/**
 * ClassName:svn 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年2月21日 上午9:08:32 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * svn获取
 *
 * @author youzongxu
 * @date 2018/9/7
 */
public class svn {
    private String userName = "";
    private String password = "";
    private String urlString = "";
    /**
     * 临时文件
     */
    private String tempDir = System.getProperty("java.io.tmpdir");
    private DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
    private Random random = new Random();

    private SVNRepository repos;
    private ISVNAuthenticationManager authManager;
    public svn(){
    
    }
    public svn(String u,String p,String url) {
        try {
            userName = u;
            password = p;
            urlString = url;
            init();
        } catch (SVNException e) {
            e.printStackTrace();
        }
    }

    public void init() throws SVNException{
        authManager = SVNWCUtil.createDefaultAuthenticationManager(new File(tempDir+"/auth"), userName, password.toCharArray());
        options.setDiffCommand("-x -w");
        repos = SVNRepositoryFactory.create(SVNURL
                .parseURIEncoded(urlString));
        repos.setAuthenticationManager(authManager);
    }

    /**
     * 统计一段时间内代码增加量
     * @param st
     * @param et
     * @return
     * @throws Exception
     */
//    public List<SvnDto> staticticsCodeAddByTime(Date st, Date et) throws Exception{
//        SVNLogEntry[] logs = getLogByTime(st, et);
//        List<SvnDto> ls = Lists.newArrayList();
//        if(logs.length > 0){
//            for(SVNLogEntry log:logs){
//                File logFile = getChangeLog(log.getRevision(), log.getRevision()-1);
//                SvnDto svnDto = new SvnDto();
//                svnDto.setAuthor(log.getAuthor());
//                svnDto.setRevision(log.getRevision());
//                svnDto.setSvnMessage(StringUtils.isNotBlank(log.getMessage())?log.getMessage():SvnTimeConstants.SvnMessageFlag);
//                svnDto.setSvnTime(log.getDate());
//                ls.add(svnDto);
//                List<SvnDataAuxiliaryFour> fourList = getChangeFileList(log.getRevision());
//                List<SvnDataAuxiliaryTwo> twoList = staticticsCodeAdd(logFile, log.getRevision());
//                if(!CollectionUtils.isEmpty(fourList)){
//                    svnDto.setFourList(fourList);
//                }
//                if(!CollectionUtils.isEmpty(twoList)){
//                    svnDto.setTwoList(twoList);
//                }
//            }
//        }
//        return ls;
//    }

    /**获取某一版本有变动的文件路径
     * @param version
     * @return
     * @throws SVNException
     */
//    public List getChangeFileList(long version) throws SVNException{
//        List ls = new ArrayList();
//        SVNLogClient logClient = new SVNLogClient( authManager, options );
//        SVNURL url = SVNURL.parseURIEncoded(urlString);
//        String[] paths = { "." };
//        SVNRevision pegRevision = SVNRevision.create( version );
//        SVNRevision startRevision = SVNRevision.create( version );
//        SVNRevision endRevision = SVNRevision.create( version );
//        long limit = 9999L;
//        ISVNLogEntryHandler handler = new ISVNLogEntryHandler() {
//            /**
//             * This method will process when doLog() is done
//             */
//            @Override
//            public void handleLogEntry( SVNLogEntry logEntry ) {
//                Map<String, SVNLogEntryPath> maps = logEntry.getChangedPaths();
//                Set<Map.Entry<String, SVNLogEntryPath>> entries = maps.entrySet();
//                for(Map.Entry<String, SVNLogEntryPath> entry : entries){
//                    Map myMap = new HashMap<String, Object>();
//                    myMap.put("svnVersion", logEntry.getRevision());
//                    myMap.put("svnPath", entry.getValue().getPath());
//                    myMap.put("svnType", String.valueOf(entry.getValue().getType()));
//                    ls.add(myMap);
//                }
//            }
//        };
//        try {
//            logClient.doLog( url, paths, pegRevision, startRevision, endRevision, false, true, limit, handler );
//        }
//        catch ( SVNException e ) {
//            System.out.println( "Error in doLog() " );
//            e.printStackTrace();
//        }
//        return ls;
//    }
    public static void  main(String[] args) throws Exception{  
        svn svn = new svn();
        try {
            System.out.print(svn.getChangeFileList());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        for(SVNLogEntry svnLog: svnLogs){
//            System.out.println("-------------------------------");
//            System.out.println("Revision:"+svnLog.getRevision()+",Author:"+svnLog.getAuthor()+",Date:"+sDf.format(svnLog.getDate())+",message:"+svnLog.getMessage());
//            System.out.println(svnLog.getChangedPaths());
//        }
//        svn.getChangeLog(startDate,endDate);
    }
    public List getChangeFileList() throws Exception{
        String base_svn =  "http://svn.xd-info.com:9432/svn/FORP_DEV2018";
        String path = "/金元证券/003软件开发/04编码/web-admin";
        String java_path = "/src";
        String web_path = "/web";
        svn svn = new svn("zhangpengfei", "zpf1234", base_svn+path);
        SimpleDateFormat sDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = sDf.parse("2019-02-20 14:00:00");
        Date endDate = sDf.parse("2019-02-20 19:00:00");
        endDate = new Date();
        SVNLogEntry[] svnLogs =svn.getLogByTime(startDate,endDate);
        List files = svn.getChangeFileList(svnLogs);
//        Iterator<String>tmp=files.iterator();
//        while(tmp.hasNext()){
//             System.out.println(tmp.next());
//        }
        return files;
    }
    public List getChangeFileList(SVNLogEntry[] SVNLogEntries) throws SVNException{
        String path = "/金元证券/003软件开发/04编码/web-admin";
        String java_path = "/src";
        String web_path = "/web";
        String copy_path  = "D:/tomcat-8.5.32-8080/webapps/ROOT";
        System.out.println("-------------------------------");
        List ls = new ArrayList();
        for(int i=0;i<SVNLogEntries.length;i++){
//            System.out.println("Revision:"+SVNLogEntries[i].getRevision());
//            System.out.println(SVNLogEntries[i].getChangedPaths());
            Map<String,SVNLogEntryPath> myMap = SVNLogEntries[i].getChangedPaths();
            for(String key:myMap.keySet()){
                String val = key;
                String suffix = val.substring(val.lastIndexOf(".")+1);
                Boolean flag = false;
                flag  = Pattern.matches("java", suffix);
//                System.out.println("key="+key+",suffix:"+suffix+",flag:"+flag);
                //is java file replace all path
                if(flag){
                    val = val.replaceAll(path+java_path, copy_path+"/WEB-INF/classes");
                    val = val.substring(0, val.lastIndexOf(".")+1)+"class";
                }else{
                    val = val.replaceAll(path+web_path, copy_path);

                }
                //De-reprocessing
                if(!ls.contains(val)){
                    ls.add(val);
                }
            }
        }
        
        return ls;
    }
    public String getFilePath(){
//        Pattern.matches(this.search_filter, pFile.getPath()
        return "";
    }
    /**获取一段时间内，所有的commit记录
     * @param st    开始时间
     * @param et    结束时间
     * @return
     * @throws SVNException
     */
    public SVNLogEntry[] getLogByTime(Date st, Date et) throws SVNException{
        long startRevision = repos.getDatedRevision(st);
        long endRevision = repos.getDatedRevision(et);
        System.out.println("startRevision:"+startRevision+",endRevision:"+endRevision);
        @SuppressWarnings("unchecked")
        Collection<SVNLogEntry> logEntries = repos.log(new String[]{""}, null,
                startRevision, endRevision, true, true);
        SVNLogEntry[] svnLogEntries = logEntries.toArray(new SVNLogEntry[0]);
        SVNLogEntry[] svnLogEntries1 = Arrays.copyOf(svnLogEntries, svnLogEntries.length - 1);
        return svnLogEntries;
    }

    public File getChangeLog(Date st, Date et) throws SVNException {
        long startRevision = repos.getDatedRevision(st);
        long endRevision = repos.getDatedRevision(et);
        return getChangeLog(startRevision,endRevision);
    }
    /**获取版本比较日志，并存入临时文件
     * @param startVersion
     * @param endVersion
     * @return
     */
    public File getChangeLog(long startVersion, long endVersion) {
        SVNDiffClient diffClient = new SVNDiffClient(authManager, options);
        diffClient.setGitDiffFormat(true);
        File tempLogFile;
        OutputStream outputStream = null;
        String svnDiffFile;
        do {
            svnDiffFile = tempDir + "/svn_diff_file_"+startVersion+"_"+endVersion+"_"+random.nextInt(10000)+".txt";
            tempLogFile = new File(svnDiffFile);
        } while (tempLogFile != null && tempLogFile.exists());
        try {
            tempLogFile.createNewFile();
            outputStream = new FileOutputStream(svnDiffFile);
            diffClient.doDiff(SVNURL.parseURIEncoded(urlString),
                    SVNRevision.create(startVersion),
                    SVNURL.parseURIEncoded(urlString),
                    SVNRevision.create(endVersion),
                    SVNDepth.UNKNOWN, true, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tempLogFile;
    }

    /**分析变更的代码，统计代码增量
     * @param file
     * @param revision
     * @return
     * @throws Exception
     */
//    public List<SvnDataAuxiliaryTwo> staticticsCodeAdd(File file, long revision) throws Exception{
//        System.out.print("开始统计修改代码行数");
//        List<SvnDataAuxiliaryTwo> twoList = new ArrayList();
//        FileReader fileReader = new FileReader(file);
//        BufferedReader in = new BufferedReader(fileReader);
//        String line;
//        StringBuffer buffer = new StringBuffer(1024);
//        boolean start = false;
//        while((line=in.readLine()) != null){
//            if(line.startsWith("Index:")){
//                if(start){
//                    ChangeFile changeFile = parseChangeFile(buffer);
//                    if(null!=changeFile){
//                        String filePath = changeFile.getFilePath();
//                        if(getBoolean(filePath)){
//                            int oneSize = staticOneFileChange(changeFile);
//                            SvnDataAuxiliaryTwo svnDataAuxiliaryTwo = new SvnDataAuxiliaryTwo();
//                            svnDataAuxiliaryTwo.setSvnAddlines(oneSize);
//                            svnDataAuxiliaryTwo.setSvnFilepath("/"+changeFile.getFilePath());
//                            svnDataAuxiliaryTwo.setSvnReversion(revision);
//                            twoList.add(svnDataAuxiliaryTwo);
//                        }
//                    }
//                    buffer.setLength(0);
//                }
//                start = true;
//            }
//            buffer.append(line).append('\n');
//        }
//        if(buffer.length() > 0){
//            ChangeFile changeFile = parseChangeFile(buffer);
//            if(null!=changeFile){
//                String filePath = changeFile.getFilePath();
//                if(getBoolean(filePath)){
//                    int oneSize = staticOneFileChange(changeFile);
//                    SvnDataAuxiliaryTwo svnDataAuxiliaryTwo = new SvnDataAuxiliaryTwo();
//                    svnDataAuxiliaryTwo.setSvnAddlines(oneSize);
//                    svnDataAuxiliaryTwo.setSvnFilepath("/"+changeFile.getFilePath());
//                    svnDataAuxiliaryTwo.setSvnReversion(revision);
//                    twoList.add(svnDataAuxiliaryTwo);
//                }
//            }
//        }
//        in.close();
//        fileReader.close();
//        boolean deleteFile = file.delete();
//        log.info("-----delete file-----"+deleteFile);
//        return twoList;
//    }

    public boolean getBoolean(String filePath){
        String[] k = {".java", ".html", ".css", ".js", ".jsp", ".properties",".xml",".json",".sql",".wxml",".wxss"};
        List<String> strings = Arrays.asList(k);
        boolean ba = false;
        c:for (String ls:strings) {
            if(filePath.contains(ls)){
                ba = true;
                break c;
            }
        }
        return ba;
    }

    /**统计单个文件的增加行数，（先通过过滤器，如文件后缀、文件路径等等），也可根据修改类型来统计等，这里只统计增加或者修改的文件
     * @param changeFile
     * @return
     */
//    public int staticOneFileChange(ChangeFile changeFile){
//        char changeType = changeFile.getChangeType();
//        char A = 'A';
//        char M = 'M';
//        if(A == changeType){
//            return countAddLine(changeFile.getFileContent());
//        }else if(M == changeType){
//            return countAddLine(changeFile.getFileContent());
//        }
//        return 0;
//    }

    /**解析单个文件变更日志:A表示增加文件，M表示修改文件，D表示删除文件，U表示末知
     * @param str
     * @return
     */
//    public ChangeFile parseChangeFile(StringBuffer str){
//        int index = str.indexOf("\n@@");
//        if(index > 0){
//            String header = str.substring(0, index);
//            String[] headers = header.split("\n");
//            String filePath = "";
//            if(StringUtils.isNotBlank(headers[0])){
//                filePath = headers[0].substring(7);
//            }
//            char changeType = 'U';
//            boolean oldExist = !headers[2].endsWith("(nonexistent)");
//            boolean newExist = !headers[3].endsWith("(nonexistent)");
//            if(oldExist && !newExist){
//                changeType = 'D';
//            }else if(!oldExist && newExist){
//                changeType = 'A';
//            }else if(oldExist && newExist){
//                changeType = 'M';
//            }
//            int bodyIndex = str.indexOf("@@\n")+3;
//            String body = str.substring(bodyIndex);
//            if(StringUtils.isNotBlank(filePath)){
//                ChangeFile changeFile = new ChangeFile(filePath, changeType, body);
//                return changeFile;
//            }
//        }else{
//            String[] headers = str.toString().split("\n");
//            log.info("headers"+headers[0]);
//            if(StringUtils.isNotBlank(headers[0])){
//                String filePath = headers[0].substring(7);
//                ChangeFile changeFile = new ChangeFile(filePath, 'U', null);
//                return changeFile;
//            }
//        }
//        return null;
//    }


    /**通过比较日志，统计以+号开头的非空行
     * @param content
     * @return
     */
    public int countAddLine(String content){
        int sum = 0;
        if(content !=null){
            content = '\n' + content +'\n';
            char[] chars = content.toCharArray();
            int len = chars.length;
            //判断当前行是否以+号开头
            boolean startPlus = false;
            //判断当前行，是否为空行（忽略第一个字符为加号）
            boolean notSpace = false;
            for(int i=0;i<len;i++){
                char ch = chars[i];
                if(ch =='\n'){
                    //当当前行是+号开头，同时其它字符都不为空，则行数+1
                    if(startPlus && notSpace){
                        sum++;
                        notSpace = false;
                    }
                    //为下一行做准备，判断下一行是否以+头
                    if(i < len-1 && chars[i+1] == '+'){
                        startPlus = true;
                        //跳过下一个字符判断，因为已经判断了
                        i++;
                    }else{
                        startPlus = false;
                    }
                }else if(startPlus && ch > ' '){
                    //如果当前行以+开头才进行非空行判断
                    notSpace = true;
                }
            }
        }
        return sum;
    }


    /**
     * 获取提交次数
     * @param dt
     * @return
     */
    public int countSum(Date dt){
        try {
            Date now = new Date();
            SVNLogEntry[] logByTime = getLogByTime(now, dt);
            List<SVNLogEntry> svnLogEntries = Arrays.asList(logByTime);
            return svnLogEntries.size();
        } catch (SVNException e) {
            return 0;
        }
    }

    /**
     * 获取提交量
     * @param dt
     * @return
     */
    public int dmCountSum(Date dt){
        int sum = 0;
        try {
            Date now = new Date();
            SVNLogEntry[] logByTime = getLogByTime(now, dt);
            List<SVNLogEntry> svnLogEntries = Arrays.asList(logByTime);
            sum = 0;
            for (SVNLogEntry ls:svnLogEntries) {
                sum += ls.getChangedPaths().size();
            }
        } catch (SVNException e) {
            e.printStackTrace();
        }
        return sum;
    }


    /**
     * 获取：1：今天 2：近一周 3：近一个月的数据
     * @return
     */
//    public Map<String,Object> commitCount(){
//        LocalDate localDate = new DateTime().toLocalDate();
//        LocalDate localDateSeven = new DateTime().minusDays(SvnTimeConstants.seven).toLocalDate();
//        LocalDate localDateThirty = new DateTime().minusDays(SvnTimeConstants.Thirty).toLocalDate();
//        int today = countSum(localDate.toDate());
//        int sevenToday = countSum(localDateSeven.toDate());
//        int thirtyToday = countSum(localDateThirty.toDate());
//        Map<String,Object> map = Maps.newHashMap();
//        map.put("today",today);
//        map.put("sevenToday",sevenToday);
//        map.put("thirtyToday",thirtyToday);
//        return map;
//    }

//    public Map<String,Object> dmCount(){
//        Map<String,Object> map = Maps.newHashMap();
//        //日期改成时间排列
//        LocalDate now = new DateTime().toLocalDate();
//        LocalDate one = new DateTime().minusDays(SvnTimeConstants.one).toLocalDate();
//        LocalDate two = new DateTime().minusDays(SvnTimeConstants.two).toLocalDate();
//        LocalDate three = new DateTime().minusDays(SvnTimeConstants.three).toLocalDate();
//        LocalDate four = new DateTime().minusDays(SvnTimeConstants.four).toLocalDate();
//        LocalDate five = new DateTime().minusDays(SvnTimeConstants.five).toLocalDate();
//        LocalDate six = new DateTime().minusDays(SvnTimeConstants.six).toLocalDate();
//        LocalDate seven = new DateTime().minusDays(SvnTimeConstants.seven).toLocalDate();
//
//        int now_count = dmCountSum(now.toDate());
//        int one_count = dmCountSum(one.toDate())-now_count;
//        int two_count = dmCountSum(two.toDate())-one_count;
//        int three_count = dmCountSum(three.toDate())-two_count;
//        int four_count = dmCountSum(four.toDate())-three_count;
//        int five_count = dmCountSum(five.toDate())-four_count;
//        int six_count = dmCountSum(six.toDate())-five_count;
//        int seven_count = dmCountSum(seven.toDate())-six_count;
//
//        map.put("now",now_count);
//        map.put("one",one_count);
//        map.put("two",two_count);
//        map.put("three",three_count);
//        map.put("four",four_count);
//        map.put("five",five_count);
//        map.put("six",six_count);
//        map.put("seven",seven_count);
//        return map;
//    }
}