package cn.cailang.sys.Scheduled;

public enum SysScheduledStatusEnum {
    /**
     *  开启
     * */
    ENABLED(1),
    /**
     * 关闭
     * */
    DISABLED(2),
    ;

    private Integer code;

    SysScheduledStatusEnum(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}