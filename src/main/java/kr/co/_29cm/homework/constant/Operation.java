package kr.co._29cm.homework.constant;

/**
 * 명령어
 */
public enum Operation {
    O, ORDER, Q, QUIT;

    /**
     * 주문 명령어인지 확인
     * @param operation
     * @return
     */
    public static boolean isOrderOperation(String operation){
        return Operation.O.name().equalsIgnoreCase(operation) || Operation.ORDER.name().equalsIgnoreCase(operation);
    }

    /**
     * 종료 명령어인지 확인
     * @param operation
     * @return
     */
    public static boolean isQuitOperation(String operation){
        return Operation.Q.name().equalsIgnoreCase(operation) || Operation.QUIT.name().equalsIgnoreCase(operation);
    }

    /**
     * 존재하는 명령어인지 확인
     * @param operation
     * @return
     */
    public static boolean isExistOperation(String operation){
        try {
            Operation.valueOf(operation.toUpperCase());
            return true;
        }catch(Exception e){
            return false;
        }
    }


}
