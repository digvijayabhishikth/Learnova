Course Management Platform

FUNCTIONAL REQUIREMENTS

    Mentor's can register on platform
    Mentor's can create courses

    Students can register on the platform
    Students can search for courses
    Students can purchase/enroll the course/courses
    STUDENT GETS THE CERTIFICATE AFTER COMPLETING A COURSE


Non functional Requirements
    scalable - DAU : 1 million Users
        -100k enrollments in a day
    Consistency - enrollment and payment should be highly consistent
    Availability - should be available at any given time
    Latency - search & video streaming latency should be very low

API's

//Mentor
    createMentor(): POST
    createCourse(): POST
    updateCourse(): PATCH

//Student
    createStudent(): POST
    searchCourse(): GET
    buyCourse() : POST

//Core Entities
    Mentor
    Skill{skillId, name}
    MentorSkillMapping{mentor_id, skill_id}
    Course
    Student
    Enrollment(student_is, course_id, enrolledAt, progress, status}

    Reviews{review_id,student_id,course_id,rating,comment,date}

    Order{order_id,student_id,course_id,amount,status,txn_date,payment_method}

//Course
    {
    name:
    desc:
    category: //Dev or Testing //Photography
    price:
    mentor_id:
    review_list:
    status:
    language: En Hn Tel
    level: beg int adv
    }

//extra
    Certificate{id,stdId,course_id,issue_date,certificate_url}
    Cart

Capacity Estimation :
    Storage Estimation - enrollmemt takes 500 bytes includes student course and mentor approx 1Million * 500bytes = 1.8Tb
    Query per second - 24*60*60 = 86400 ~100k 100k/100k = 1 no one query per second
