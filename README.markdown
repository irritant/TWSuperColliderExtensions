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

##### Class Variables

**`referenceFrequency`** (get, set)  
The tuning reference for frequency calculation, in Hz. Defaults to `440.0`.  
  
**`referencePitchClass`** (get)  
The pitch class of the tuning reference note (`9`).  

**`referenceOctave`** (get)  
The octave of the tuning reference note (`5`).  

**`midiBaseOctave`** (get, set)  
The lowest octave for MIDI. Defaults to `-1`, which is appropriate for most MIDI systems. Set to `-2` for Yamaha systems.  

	classvar <>midiBaseOctave = -1;

##### Instance Variables

**`pitchClass`** (get, set)  
The current pitch class ranging from `0` to `11`.

**`octave`** (get, set)  
The current octave ranging from `0` to `inf`.

##### Class Methods

**`newPitch(pitchClass: 0, octave: 0)`**  
Creates a new `TWPitch` instance with a given pitch class and octave.  

**`newPitchWithMIDINoteNumber(noteNumber: 0)`**  
Creates a new `TWPitch` instance with a given MIDI note number.  

##### Instance Methods

**`initWithPitchClass(pitchClass: 0, octave: 0)`**  
Initializes the object with a given pitch class and octave. This method is invoked by `TWPitch.newPitch()` and should not be invoked manually.   

**`initWithMIDINoteNumber(noteNumber: 0)`**
Initializes the object with a given MIDI note number. This method is invoked by `TWPitch.newPitchWithMIDINoteNumber()` and should not be invoked manually.  

**`midiNoteNumber`**  
Calculates and returns the MIDI note number for the current pitch class and octave.  

**`midiOctave`**  
Returns the current octave, offset by the value of `TWPitch.midiBaseOctave`.  

**`frequency`**  
Calculates and returns the frequency in Hz for the current pitch class and octave.  
