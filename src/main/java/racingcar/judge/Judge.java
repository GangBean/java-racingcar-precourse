package racingcar.judge;

import racingcar.attendgroup.AttendGroup;
import racingcar.car.Car;

public class Judge {
    private AttendGroup attendGroup;
    private Integer winnerPosition;

    public Judge(AttendGroup attendCarList) {
        this.attendGroup = attendCarList;
    }

    public AttendGroup getAttendGroup() {
        return this.attendGroup;
    }

    public boolean playRace() {
        for (Car car:this.attendGroup.getAttendCarList()) {
            car.setMoveCount();
            car.moveCar();
        }
        return true;
    }

    public String getRaceResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car:this.attendGroup.getAttendCarList()) {
            stringBuilder.append(getCarRaceResult(car)).append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public String getCarRaceResult(Car car) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(car.getName()).append(" : ");
        int carPosition = car.getCarPosition();
        for (int i=0; i<carPosition; i++) {
            stringBuilder.append("-");
        }
        return stringBuilder.toString();
    }

    public String getRaceWinner() {
        this.getWinnerPosition();
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car:this.attendGroup.getAttendCarList()) {
            stringBuilder.append(judgeWinner(car));
        }
        String winner = stringBuilder.toString();
        return winner.substring(0, winner.length()-1);
    }

    public String judgeWinner(Car car) {
        StringBuilder stringBuilder = new StringBuilder();
        if (car.getCarPosition() == this.winnerPosition) {
            stringBuilder.append(car.getName()).append(",");
        }
        return stringBuilder.toString();
    }

    public void getWinnerPosition() {
        int winnerPosition = 0;
        for (Car car:this.attendGroup.getAttendCarList()) {
            winnerPosition = Math.max(car.getCarPosition(), winnerPosition);
        }
        this.winnerPosition = winnerPosition;
    }
}
