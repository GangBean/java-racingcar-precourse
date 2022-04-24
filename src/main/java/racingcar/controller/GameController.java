package racingcar.controller;

import racingcar.car.Car;
import racingcar.judge.Judge;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    public static final String NORMAL_CONDITION = "OK";
    public static final String ILLEGAL_ARGUMENT_EXCEPTION = "ILLEGAL_ARGUMENT";
    public static final String ILLEGAL_STATE_EXCEPTION = "ILLEGAL_STATE";
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
        String validate = validateParticipantInput();
        while (!NORMAL_CONDITION.equals(validate)) {
            this.userInterfaceController.printParticipantInputError(validate);
            validate = validateParticipantInput();
        }
    }

    private String validateParticipantInput() {
        try {
            registerParticipant(this.userInterfaceController.requireCarNameInput());
            return NORMAL_CONDITION;
        } catch (IllegalArgumentException e) {
            return ILLEGAL_ARGUMENT_EXCEPTION;
        } catch (IllegalStateException e) {
            return ILLEGAL_STATE_EXCEPTION;
        }
    }

    private void registerParticipant(String participant) {
        List<Car> cars = new ArrayList<>();
        String[] participants = participant.split(",");
        if (participants.length == 0) {
            throw new IllegalStateException();
        }
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
