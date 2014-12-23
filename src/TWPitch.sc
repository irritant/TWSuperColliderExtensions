TWPitch {

	classvar <>referenceFrequency = 440.0;
	classvar <referencePitchClass = 9;
	classvar <referenceOctave = 5;
	classvar <>midiBaseOctave = -1;

	var <>pitchClass = 0;
	var <>octave = 0;

	// Initialization:

	*newPitch {
		arg pitchClass = 0, octave = 0;
		^super.new.initWithPitchClass(
			pitchClass: pitchClass, 
			octave: octave);
	}

	*newPitchWithMIDINoteNumber {
		arg noteNumber = 0;
		^super.new.initWithMIDINoteNumber(
			noteNumber: noteNumber);
	}

	initWithPitchClass {
		arg pitchClass = 0, octave = 0;
		this.pitchClass = pitchClass;
		this.octave = octave;
	}

	initWithMIDINoteNumber {
		arg noteNumber = 0;
		this.pitchClass = noteNumber % 12;
		this.octave = (noteNumber / 12).floor;
	}

	// Conversion:

	midiNoteNumber {
		^(this.pitchClass + (this.octave * 12));
	}

	midiOctave {
		^(this.octave + TWPitch.midiBaseOctave);
	}

	frequency {
		var refOctave = this.octave - TWPitch.referenceOctave;
		var refPitchClass = this.pitchClass - TWPitch.referencePitchClass;
		^(TWPitch.referenceFrequency * (2.0 pow: (refOctave + (refPitchClass / 12.0))));
	}

}