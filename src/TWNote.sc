TWNote {
	
	var <>pitch = nil;
	var <>duration = nil;
	var <>isRest = false;

	*newNote {
		arg pitch = TWPitch.newPitch, duration = TWDuration.newDuration, isRest = false;
		^super.new.initWithPitchAndDuration(
			pitch: pitch,
			duration: duration,
			isRest: isRest);
	}

	*newNoteWithPitchClass {
		arg pitchClass = 0, octave = 0, durationNumerator = 1, durationDenominator = 1;
		^super.new.initWithPitchAndDuration(
			pitch: TWPitch.newPitch(
				pitchClass: pitchClass, 
				octave: octave),
			duration: TWDuration.newDuration(
				numerator: durationNumerator,
				denominator: durationDenominator));
	}

	*newNoteWithMIDINoteNumber {
		arg noteNumber = 0, durationNumerator = 1, durationDenominator = 1;
		^super.new.initWithPitchAndDuration(
			pitch: TWPitch.newPitchWithMIDINoteNumber(
				noteNumber: noteNumber),
			duration: TWDuration.newDuration(
				numerator: durationNumerator,
				denominator: durationDenominator));
	}

	*newRest {
		arg durationNumerator = 1, durationDenominator = 1;
		^super.new.initWithPitchAndDuration(
			duration: TWDuration.newDuration(
				numerator: durationNumerator,
				denominator: durationDenominator),
			isRest: true);
	}

	initWithPitchAndDuration {
		arg pitch = TWPitch.newPitch, duration = TWDuration.newDuration, isRest = false;
		this.pitch = pitch;
		this.duration = duration;
		this.isRest = isRest;
	}

}