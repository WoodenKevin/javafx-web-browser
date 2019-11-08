package pers.woodenkevin.webbrowser.controller;

import pers.woodenkevin.webbrowser.controller.mediator.ControllerMediator;
import pers.woodenkevin.webbrowser.model.operator.DataOperator;

public class Controller {
    protected ControllerMediator mediator;
    protected DataOperator operator;

    public Controller() {
        operator = DataOperator.getInstance();
        mediator = ControllerMediator.getInstance();
    }

    // 获取欢迎页面URL
    // protected String getWelcomePageURL() {
    //    return this.getClass().getResource("/pers/woodenkevin/webbrowser/resource/page/welcome.html").toString();
    // }

    protected String getIconURL(String iconName) {
        return "pers/woodenkevin/webbrowser/resource/icon/" + iconName;
    }
}