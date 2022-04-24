package racingcar.controller;

import camp.nextstep.edu.missionutils.Console;

public class UserInterfaceController {
    public UserInterfaceController() {
    }

    public String requireCarNameInput() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        return Console.readLine();
    }

    public Integer requireRacingRoundNumberInput() {
        System.out.println("시도할 횟수는 몇회인가요?");
        return Integer.parseInt(Console.readLine());
    }

    public void printAnnounceRoundResult() {
        System.out.println("실행 결과");
    }

    public void printOneRoundResult(String result) {
        System.out.print(result);
    }

    public void printRaceWinner(String winner) {
        System.out.print("최종 우승자: "+winner);
    }
}
