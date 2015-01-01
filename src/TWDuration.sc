TWDuration {

	classvar protectedReferenceTempo = 60.0;

	var protectedNumerator = 1;
	var protectedDenominator = 1;

	// Reference Values:

	*referenceTempo {
		^protectedReferenceTempo;
	}

	*setReferenceTempo {
		arg tempo = 60.0;
		protectedReferenceTempo = tempo.max(1.0);
	}

	// Numerator & Denominator:

	numerator {
		^protectedNumerator;
	}

	setNumerator {
		arg numerator = 1;
		protectedNumerator = numerator.ceil.max(1);
	}

	denominator {
		^protectedDenominator;
	}

	setDenominator {
		arg denominator = 1;
		protectedDenominator = denominator.ceil.max(1);
	}

	// Initialization:

	*newDuration {
		arg numerator = 1, denominator = 1;
		^super.new.initWithFraction(
			numerator: numerator, 
			denominator: denominator);
	}

	initWithFraction {
		arg numerator = 1, denominator = 1;
		this.setNumerator(numerator);
		this.setDenominator(denominator);
	}

	// Conversion:

	decimal {
		^(this.numerator / this.denominator);
	}

	seconds {
		arg tempo = TWDuration.referenceTempo;
		var beatDuration = 60.0 / tempo.max(1.0);
		var barDuration = beatDuration * 4.0;
		^(barDuration * this.decimal);
	}

	milliseconds {
		arg tempo = TWDuration.referenceTempo;
		^(this.seconds(tempo) * 1000.0);
	}

}