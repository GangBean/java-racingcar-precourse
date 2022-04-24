package racingcar.car;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    public static final int MIN_MOVE_COUNT = 0;
    public static final int MAX_MOVE_COUNT = 9;
    public static final int MOVE_STANDARD_COUNT = 4;
    public static final boolean MOVE = true;
    private String name;
    private int moveCount;
    private boolean moveOrStop;
    private int carPosition;

    public Car(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5글자 이하만 가능합니다.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public void setMoveCount() {
        this.moveCount = Randoms.pickNumberInRange(MIN_MOVE_COUNT, MAX_MOVE_COUNT);
    }

    public boolean getMoveOrStop() {
        this.moveOrStop = (this.moveCount>= MOVE_STANDARD_COUNT);
        return this.moveOrStop;
    }

    public int getCarPosition() {
        return this.carPosition;
    }

    public void moveCar() {
        if (this.getMoveOrStop()==MOVE) {
            this.carPosition++;
        }
        this.moveCount = 0;
    }
}
