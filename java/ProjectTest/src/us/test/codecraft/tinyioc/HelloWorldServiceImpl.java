package us.test.codecraft.tinyioc;

/**
 * @author yihua.huang@dianping.com
 */
public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    @Override
    public void helloWorld(){
        outputService.output(text);
    }
    public HelloWorldServiceImpl(){
    }
    public  HelloWorldServiceImpl(String text,OutputService outputService){
        this.text = text;
        this.outputService= outputService;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

}
