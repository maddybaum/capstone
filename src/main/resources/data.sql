INSERT INTO accommodations(Accommodation_ID, Accommodation_Name, Accommodation_Description) values
(1, 'Translator', 'Student requires translation to one or more languages'),
(2, 'Wheelchair accessibility', 'Student requires wheelchair accessibility for all course locations'),
(3, 'Breaks', 'Student requires breaks throughout school day'),
(4, 'Teacher check in', 'Student requires teacher check in during class'),
(5, 'Peer buddy', 'Student should be assigned a peer buddy for support during class'),
(6, 'Proximity check', 'Teacher should check in with student using proximity'),
(7, 'Preferential seating', 'Student should always be seated close to where instruction occurs'),
(8, 'Extended time 1.5', 'Student requires 1.5x extended time on all assessments'),
(9, 'Extended time 2', 'Student requires 2x extended time on all assessments'),
(10, 'Sensory tools', 'Student requires access to sensory tools during classtime');
commit;

INSERT INTO Course(Course_ID, Course_Name, Number_Of_Courses_Elapsed) values
(15, 'TestCourse1', 4),
(16, 'TestCourse2', 7);
commit;

