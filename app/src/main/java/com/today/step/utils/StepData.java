package com.today.step.utils;

public class StepData {
	public static int step = 0;

	public StepData(int step) {
		this.step = step;
	}

	public static int getStep() {
		return step;
	}

	public static void setStep(int step) {
		StepData.step = step;
	}
}
