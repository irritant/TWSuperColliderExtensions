# TWSuperColliderExtensions

##### Extensions for the [SuperCollider](https://github.com/supercollider/supercollider) audio programming language.

### Author 
[Tony Wallace](http://tonywallace.ca) (Github: [Irritant](https://github.com/irritant))  

### License

TWSuperColliderExtensions is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version. See [opensource.org/licenses/GPL-3.0](http://opensource.org/licenses/GPL-3.0) for details.  

### Installation
Clone the repository into your user or system extension directory. You can locate these directories by evluating the following commands in SuperCollider:

	Platform.userExtensionDir;   // Extensions available only to your user account
	Platform.systemExtensionDir; // Extensions available to all users on the machine

SuperCollider will compile the contents of these directories on startup. If SuperCollider is already running, you can recompile using `Language > Recompile Class Library`. If you use the [SuperCollider package for Sublime Text](https://github.com/irritant/supercollider-package-for-sublime-text), use `Tools > SuperCollider > Recompile Class Library`.  

### TWPitch
TWPitch facilitates the use of pitch class notation in SuperCollider. Pitch class notation is a system in which pitches and their enharmonic equivalents are identified by integers ranging from `0` to `11`:

	0:  C
	1:  C sharp / D flat
	2:  D
	...
	9:  A
	10: A sharp / B flat
	11: B 

Octaves are identified by integers ranging from `0` to `inf`. Pitch class `0` in octave `0` produces C at ~8.176Hz. Pitch class `9` in octave `5` produces A at 440.0Hz.  

#### Class Methods

##### referenceFrequency
Returns the tuning reference for frequency calculation in Hz. Defaults to `440.0`.  

##### setReferenceFrequency(frequency: 440.0)
Sets the tuning reference for frequency calculation in Hz, limited to a minimum value of `1.0`.  
  
##### referencePitchClass
Returns the pitch class of the tuning reference note. Defaults to `9`.  

##### setReferencePitchClass(pitchClass: 9)
Sets the pitch class of the tuning reference note, limited to integers in the range `0..11`.   

##### referenceOctave
Returns the octave of the tuning reference note. Defaults to `5`.  

##### setReferenceOctave(octave: 5)
Sets the octave of the tuning reference note, limited to integers in the range `0..inf`.  

##### midiBaseOctave
Returns the MIDI base octave. Defaults to `-1`, which is appropriate for most MIDI systems.    

##### setMIDIBaseOctave(octave: -1)
Sets the MIDI base octave, limited to integers in the range `-2..0`.  

##### newPitch(pitchClass: 0, octave: 0)
Returns a new `TWPitch` object with a given pitch class and octave.  

##### newPitchWithMIDINoteNumber(noteNumber: 0)
Returns a new `TWPitch` object with a given MIDI note number.  

#### Instance Methods

##### pitchClass
Returns the instance's pitch class.  

##### setPitchClass(pitchClass: 0)
Sets the instance's pitch class, limited to integers in the range `0..11`.  

##### octave
Returns the instance's octave.  

##### setOctave(octave: 0)
Sets the instance's octave, limited to integers in the range `0..inf`.  

##### initWithPitchClass(pitchClass: 0, octave: 0)
Initializes the object with a given pitch class and octave. This method is invoked by `TWPitch.newPitch()`. You should never need to call it manually.  

##### initWithMIDINoteNumber(noteNumber: 0)
Initializes the object with a given MIDI note number. This method is invoked by `TWPitch.newPitchWithMIDINoteNumber()`. You should never need to call it manually.  

##### midiNoteNumber
Calculates and returns the MIDI note number for the current pitch class and octave.  

##### midiOctave
Returns the current octave, offset by the value of `TWPitch.midiBaseOctave`.  

##### frequency
Calculates and returns the frequency in Hz for the current pitch class and octave.  

### TWDuration
TWDuration facilitates conversion from fractional note durations (eg. `1/4`, `1/8`, `1/16`) to time in seconds and milliseconds.  

#### Class Methods

##### referenceTempo
Returns the default tempo used for time calculations in beats per minute. Defaults to `60.0`.  

##### setReferenceTempo(tempo: 60.0)
Sets the default tempo used for time calculations in beats per minute, limited to the range `1.0..inf`.  

##### newDuration(numerator: 1, denominator: 1)
Returns a new `TWDuration` object with a given numerator and denominator.  

#### Instance Methods

##### numerator
Returns the instance's numerator.  

##### setNumerator(numerator: 1)
Sets the instance's numerator, limited to the range `1..inf`.  

##### denominator
Returns the instance's denominator.  

##### setDenominator(denominator: 1)
Sets the instance's denominator, limited to the range `1..inf`.  

##### initWithFraction(numerator: 1, denominator: 1)
Initializes the object with a given numerator and denominator. This method is invoked by `TWDuration.newDuration()`. You should never need to call it manually.  

##### decimal
Returns the instance's fraction as a decimal.   

##### seconds(tempo: TWDuration.referenceTempo)
Returns the instance's duration for a given tempo, in seconds. If the `tempo` argument is omitted, `TWDuration.referenceTempo` is used by default.  

##### milliseconds(tempo: TWDuration.referenceTempo)
Returns the instance's duration for a given tempo, in milliseconds. If the `tempo` argument is omitted, `TWDuration.referenceTempo` is used by default.  

### TWNote
TWNote combines one instance each of `TWPitch` and `TWDuration` to create a description of a musical note.  

#### Instance Variables

##### pitch (get, set)
An instance of `TWPitch`.  

##### duration (get, set)
An instance of `TWDuration`.  

##### isRest (get, set)
A `Boolean` indicating whether or not the note should be interpreted as a rest.  

#### Class Methods

##### newNote(pitch: TWPitch.newPitch, duration: TWDuration.newDuration, isRest: false)
Returns a new `TWNote` object with instances of `TWPitch` and `TWDuration`. The `isRest` argument may be used to indicate that the note should be interpreted as a rest.  

##### newNoteWithPitchClass(pitchClass: 0, octave: 0, durationNumerator: 1, durationDenominator: 1)
Returns a new `TWNote` object with a given pitch class, octave and duration. See `TWPitch` and `TWDuration` for descriptions of the `pitchClass`, `octave`, `durationNumerator` and `durationDenominator` arguments.  

##### newNoteWithMIDINoteNumber(noteNumber: 0, durationNumerator: 1, durationDenominator: 1)
Returns a new `TWNote` object with a given MIDI note number and duration. See `TWPitch` and `TWDuration` for descriptions of the `pitchClass`, `octave`, `durationNumerator` and `durationDenominator` arguments.  

##### newRest(durationNumerator: 1, durationDenominator: 1)
Returns a new `TWNote` object configured as a rest with a given duration. See `TWDuration` for descriptions of the `durationNumerator` and `durationDenominator` arguments.  

#### Instance Methods

##### initWithPitchAndDuration(pitch: TWPitch.newPitch, duration: TWDuration.newDuration, isRest: false)
Initializes the object with instances of `TWPitch` and `TWDuration`. This method is invoked by `TWNote.newNote`, `TWNote.newNoteWithPitchClass`, `TWNote.newNoteWithMIDINoteNumber` and `TWNote.newRest`. You should never need to call it manually.  
