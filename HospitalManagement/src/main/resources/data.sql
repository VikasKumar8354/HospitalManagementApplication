INSERT INTO patient_table (name, gender, birth_date, email, blood_group)
VALUES
 ('John Doe', 'Male', '1990-05-15', 'john.doe@example.com', ' O_POS');
 ('Jane Smith', 'Female', '1988-08-22', 'jane.smith@example.com', 'A_POS');
 ('Alice Johnson', 'Female', '1992-07-08', 'alice.j@example.com', ' B_NEG');
 ('Robert Brown', 'Male', '1982-03-12', 'robert.brown@example.com', 'AB_POS');
 ('Emily Davis', 'Female', '1995-11-30', 'emily.davis@example.com', 'O_NEG');

 INSERT INTO doctor (name, specialization, email)
  VALUES
 ('Dr. John Smith', 'Cardiology', 'john.smith@example.com'),
 ('Dr. Arjun Patel', 'Orthopedics', 'arjun.patel@example.com'),
 ('Dr. Sophia Williams', 'Dermatology', 'sophia.williams@example.com'),
 ('Dr. Ramesh Kumar', 'Pediatrics', 'ramesh.kumar@example.com');

 INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
 VALUES
 ('09:00:00', 'Dental Checkup', 201, 101),
 ('10:30:00', 'Eye Consultation', 202, 102),
 ('11:45:00', 'Follow-up', 201, 103),
 ('14:00:00', 'Physiotherapy', 203, 104),
 ('15:15:00', 'Skin Allergy', 202, 105);

