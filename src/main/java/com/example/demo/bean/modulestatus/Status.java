package com.example.demo.bean.modulestatus;

public class Status {
    // 读卡器内回收盒的状态
    private int IdcBinSt;

    // 吞卡数
    private int IdcCardNum;

    // IC 芯片的状态
    private int IdcChipPowerSt;

    // 媒介状态
    private int MediaSt;

    // 设备状态
    private int DevSt;

    // 加密模块的状态
    private int PinEncSt;

    // 发卡器卡单元状态
    private int DispenserSt;

    // 卡口门状态
    private int ShutterSt;

    // 传送单元状态
    private int TransportSt;

    // 碳粉或墨水供应状态或打印色带的状态
    private int TonerSt;

    // 墨水状态
    private int InkSt;

    // 指定单纸张供应或上位纸张供应状态
    private int PtrPaperUpSt;

    // 指定下位纸供应状态
    private int PtrPaperLowSt;

    // 指定外部纸张供应状态
    private int PtrPaperExtSt;

    // 二维码扫描模块状态
    private int BCRScannerSt;

    // 现金发放单元状态
    // private int DispenserSt;

    // 接受钞箱状态
    private int AcceptorSt;

    // 纸币读取设备状态
    private int BanknoteReaderSt;

    // 中间栈状态
    private int IntermediateStackerSt;

    // 保险门状态
    private int SafeDoorSt;

    // 中间栈用户接入状态
    private int StackerItemsSt;

    public void setIdcBinSt(int IdcBinSt) {
        this.IdcBinSt = IdcBinSt;
    }

    public int getIdcBinSt() {
        return this.IdcBinSt;
    }

    public void setIdcCardNum(int IdcCardNum) {
        this.IdcCardNum = IdcCardNum;
    }

    public int getIdcCardNum() {
        return this.IdcCardNum;
    }

    public void setIdcChipPowerSt(int IdcChipPowerSt) {
        this.IdcChipPowerSt = IdcChipPowerSt;
    }

    public int getIdcChipPowerSt() {
        return this.IdcChipPowerSt;
    }

    public void setMediaSt(int MediaSt) {
        this.MediaSt = MediaSt;
    }

    public int getMediaSt() {
        return this.MediaSt;
    }

    public void setDevSt(int DevSt) {
        this.DevSt = DevSt;
    }

    public int getDevSt() {
        return this.DevSt;
    }

    public void setPinEncSt(int PinEncSt) {
        this.PinEncSt = PinEncSt;
    }

    public int getPinEncSt() {
        return this.PinEncSt;
    }

    public void setDispenserSt(int DispenserSt) {
        this.DispenserSt = DispenserSt;
    }

    public int getDispenserSt() {
        return this.DispenserSt;
    }

    public void setShutterSt(int ShutterSt) {
        this.ShutterSt = ShutterSt;
    }

    public int getShutterSt() {
        return this.ShutterSt;
    }

    public void setTransportSt(int TransportSt) {
        this.TransportSt = TransportSt;
    }

    public int getTransportSt() {
        return this.TransportSt;
    }

    public void setAcceptorSt(int AcceptorSt) {
        this.AcceptorSt = AcceptorSt;
    }

    public int getAcceptorSt() {
        return this.AcceptorSt;
    }

    public void setBanknoteReaderSt(int BanknoteReaderSt) {
        this.BanknoteReaderSt = BanknoteReaderSt;
    }

    public int getBanknoteReaderSt() {
        return this.BanknoteReaderSt;
    }

    public void setIntermediateStackerSt(int IntermediateStackerSt) {
        this.IntermediateStackerSt = IntermediateStackerSt;
    }

    public int getIntermediateStackerSt() {
        return this.IntermediateStackerSt;
    }

    public void setSafeDoorSt(int SafeDoorSt) {
        this.SafeDoorSt = SafeDoorSt;
    }

    public int getSafeDoorSt() {
        return this.SafeDoorSt;
    }

    public void setStackerItemsSt(int StackerItemsSt) {
        this.StackerItemsSt = StackerItemsSt;
    }

    public int getStackerItemsSt() {
        return this.StackerItemsSt;
    }

    public int getTonerSt() {
        return TonerSt;
    }

    public void setTonerSt(int tonerSt) {
        TonerSt = tonerSt;
    }

    public int getInkSt() {
        return InkSt;
    }

    public void setInkSt(int inkSt) {
        InkSt = inkSt;
    }

    public int getPtrPaperUpSt() {
        return PtrPaperUpSt;
    }

    public void setPtrPaperUpSt(int ptrPaperUpSt) {
        PtrPaperUpSt = ptrPaperUpSt;
    }

    public int getPtrPaperLowSt() {
        return PtrPaperLowSt;
    }

    public void setPtrPaperLowSt(int ptrPaperLowSt) {
        PtrPaperLowSt = ptrPaperLowSt;
    }

    public int getPtrPaperExtSt() {
        return PtrPaperExtSt;
    }

    public void setPtrPaperExtSt(int ptrPaperExtSt) {
        PtrPaperExtSt = ptrPaperExtSt;
    }

    public int getBCRScannerSt() {
        return BCRScannerSt;
    }

    public void setBCRScannerSt(int bCRScannerSt) {
        BCRScannerSt = bCRScannerSt;
    }

    @Override
    public String toString() {
        return "Status [AcceptorSt=" + AcceptorSt + ", BCRScannerSt=" + BCRScannerSt + ", BanknoteReaderSt="
                + BanknoteReaderSt + ", DevSt=" + DevSt + ", DispenserSt=" + DispenserSt + ", IdcBinSt=" + IdcBinSt
                + ", IdcCardNum=" + IdcCardNum + ", IdcChipPowerSt=" + IdcChipPowerSt + ", InkSt=" + InkSt
                + ", IntermediateStackerSt=" + IntermediateStackerSt + ", MediaSt=" + MediaSt + ", PinEncSt=" + PinEncSt
                + ", PtrPaperExtSt=" + PtrPaperExtSt + ", PtrPaperLowSt=" + PtrPaperLowSt + ", PtrPaperUpSt="
                + PtrPaperUpSt + ", SafeDoorSt=" + SafeDoorSt + ", ShutterSt=" + ShutterSt + ", StackerItemsSt="
                + StackerItemsSt + ", TonerSt=" + TonerSt + ", TransportSt=" + TransportSt + "]";
    }

}