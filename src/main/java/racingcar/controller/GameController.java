package racingcar.controller;

import racingcar.car.Car;
import racingcar.judge.Judge;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private UserInterfaceController userInterfaceController;
    private List<Car> attendCarList;
    private int gameRoundNumber;
    private Judge judge;

    public void playGame() {
        this.userInterfaceController = new UserInterfaceController();
        requestParticipantInput();
        requestRacingRoundNumberInput();
        requestPlayRacing();
        requestAnnounceRaceWinner();
    }

    private void requestParticipantInput() {
        List<Car> cars = new ArrayList<>();
        String participant = this.userInterfaceController.requireCarNameInput();
        String[] participants = participant.split(",");
        for (String name:participants) {
            cars.add(new Car(name));
        }
        this.attendCarList = cars;
        this.judge = new Judge(attendCarList);
    }

    private void requestRacingRoundNumberInput() {
        this.gameRoundNumber = this.userInterfaceController.requireRacingRoundNumberInput();
    }

    private void requestPlayRacing() {
        this.userInterfaceController.printAnnounceRoundResult();
        for (int i=0; i<this.gameRoundNumber; i++) {
            requestOneRoundRacing();
        }
    }

    private void requestOneRoundRacing() {
        this.judge.playRace();
        this.userInterfaceController.printOneRoundResult(this.judge.getRaceResult());
    }

    private void requestAnnounceRaceWinner() {
        this.userInterfaceController.printRaceWinner(this.judge.getRaceWinner());
    }
}
