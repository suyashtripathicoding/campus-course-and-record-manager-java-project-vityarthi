package edu.ccrm.domain;

public enum Grade {
S(10),
A(9),
B(8),
C(7),
D(6),
F(0);
	
private final int GradePoints;

Grade(int GradePoints ){
	this.GradePoints = GradePoints;
}

public int getGradePoints() {
	return GradePoints;
}
}
