package racingcar.judge;

import racingcar.car.Car;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Judge {
    private List<Car> attendCarList;
    private Integer wholeRoundNumber;
    private Integer winnerPosition;

    public Judge(List<Car> attendCarList) {
        this.attendCarList = attendCarList;
    }

    public Judge(List<Car> cars, int roundNumber) {
        this.attendCarList = cars;
        this.wholeRoundNumber = roundNumber;
    }

    public List<Car> getAttendCarList() {
        return this.attendCarList;
    }

    public boolean playRace() {
        if (this.attendCarList.size()==0) {
            return false;
        }
        for (Car car:this.attendCarList) {
            car.setMoveCount();
            car.moveCar();
        }
        return true;
    }
    public boolean playWholeRace() {
        int round = 0;
        boolean endOk = true;
        while (round<this.wholeRoundNumber) {
            endOk = (endOk && playRace());
            round++;
        }
        return endOk;
    }

    public String getRaceResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Car car:this.attendCarList) {
            stringBuilder.append(getCarRaceResult(car)).append("\n");
        }
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
        List<String> raceResult = new ArrayList<>();
        for (Car car:this.attendCarList) {
            if (this.judgeWinner(car)) {
                raceResult.add(car.getName());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String winner:raceResult) {
            stringBuilder.append(winner).append(",");
        }
        String winner = stringBuilder.toString();
        return winner.substring(0, winner.length()-1);
    }

    public boolean judgeWinner(Car car) {
        return car.getCarPosition() == this.winnerPosition;
    }

    public void getWinnerPosition() {
        int winnerPosition = 0;
        for (Car car:this.attendCarList) {
            winnerPosition = Math.max(car.getCarPosition(), winnerPosition);
        }
        this.winnerPosition = winnerPosition;
    }
}
