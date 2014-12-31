TWPitch {

	classvar protectedReferenceFrequency = 440.0;
	classvar protectedReferencePitchClass = 9;
	classvar protectedReferenceOctave = 5;
	classvar protectedMIDIBaseOctave = -1;

	var protectedPitchClass = 0;
	var protectedOctave = 0;

	// Reference Values:

	*referenceFrequency {
		^protectedReferenceFrequency;
	}	

	*setReferenceFrequency {
		arg frequency = 440.0;
		protectedReferenceFrequency = frequency.max(1.0);
	}

	*referencePitchClass {
		^protectedReferencePitchClass;
	}	

	*setReferencePitchClass {
		arg pitchClass = 9;
		protectedReferencePitchClass = pitchClass.floor.max(0).min(11);
	}

	*referenceOctave {
		^protectedReferenceOctave;
	}	

	*setReferenceOctave {
		arg octave = 5;
		protectedReferenceOctave = octave.floor.max(0);
	}

	*midiBaseOctave {
		^protectedMIDIBaseOctave;
	}	

	*setMIDIBaseOctave {
		arg octave = -1;
		protectedMIDIBaseOctave = octave.floor.max(-2).min(0);
	}

	// Pitch Class & Octave:

	pitchClass {
		^protectedPitchClass;
	}

	setPitchClass {
		arg pitchClass = 0;
		protectedPitchClass = pitchClass.floor.max(0).min(11);
	}

	octave {
		^protectedOctave;
	}

	setOctave {
		arg octave = 0;
		protectedOctave = octave.floor.max(0);
	}

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
		this.setPitchClass(pitchClass);
		this.setOctave(octave);
	}

	initWithMIDINoteNumber {
		arg noteNumber = 0;
		this.setPitchClass(noteNumber % 12);
		this.setOctave(noteNumber / 12);
	}

	// Conversion:

	midiNoteNumber {
		^(this.pitchClass + (this.octave * 12));
	}

	midiOctave {
		^(this.octave + TWPitch.midiBaseOctave);
	}

	frequency {
		arg referenceFrequency = TWPitch.referenceFrequency;
		var refOctave = this.octave - TWPitch.referenceOctave;
		var refPitchClass = this.pitchClass - TWPitch.referencePitchClass;
		^(referenceFrequency.max(1.0) * (2.0 pow: (refOctave + (refPitchClass / 12.0))));
	}

}