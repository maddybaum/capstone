//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const getTeacherCoursesButton = document.getElementById("getTeacherCourses")
const addStudentButton = document.getElementById("addStudent")
const getAllStudentsButton = document.getElementById("getAllStudents")
const studentTable = document.getElementById("studentTable")
const studentTableBody = document.getElementById("studentTableBody")
const coursesTableBody = document.getElementById("coursesTableBody")
const addCourseModal = document.querySelector('.modal-content')
const addCourseButton = document.getElementById('addCourseButton')
const closeRosterModalBtn = document.getElementById('closeRosterModal')
const courseNameInput = document.getElementById('addCourseName')
const courseTeacherInput = document.getElementById('addCourseTeacher')
const courseElapsedInput = document.getElementById('addCourseElapsed')
const addCourseModalButton = document.getElementById('addCourseModalButton')
const rosterTable = document.getElementById('courseRosterTable')
const rosterModal = document.getElementById('rosterModal')
const courseRosterh4 = document.getElementById('courseRosterh4')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/course"

window.onload = function(){
closeAddCourseModal();
closeRosterModal()
getTeacherCourses(1);
getAllStudents();
    //Get teachers courses to populate
    //Get teachers students to populate
}
//Need to fix so that it returns values for the user logged in

function closeAddCourseModal(){
    addCourseModal.style.display = "none"
}

function openAddCourseModal(){
    addCourseModal.style.display = "block"
}

async function increaseCourseElapsed(courseId){
    let bodyObj = {
        courseId: courseId,
    }
    //make object and pass 2 IDs in
    //pass in param and body
    //param is student id, and body is the info on which accommodation to edit
    const response = await fetch(`http://localhost:8080/api/course/${courseId}`, {
        method: "PUT",
        headers: {
            'Content-type': 'application/json'
        }

    })
        .catch(err => console.error(err))
}


async function getTeacherCourses(teacherId) {
      await fetch(`http://localhost:8080/api/course/teacher/${teacherId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => {
            data.forEach(elem => {
                let tableRow = document.createElement("tr")
                tableRow.innerHTML = `<th scope = "row" class = "${elem.courseId}">${elem.courseId}</th>
                                 <td>${elem.courseName}</td>
                                  <td id = "${elem.courseId}received">${elem.numberOfCoursesElapsed}</td>
                                 <td onClick = "increaseCourseElapsed(${elem.courseId})"><button type="button" class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
            </svg></button></td>
<td><button type="button" class="btn btn-outline-danger" onClick = "deleteCourse(${elem.courseId})"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
            </svg></button></td>   
            <td><button type="button" class="btn btn-outline-info" onClick = "getCourseRoster(${elem.courseId})"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard" viewBox="0 0 16 16">
  <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z"/>
  <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
</svg></button></td>      `


                coursesTableBody.appendChild(tableRow)

            })
                .catch(err => console.error(err))
        })
}
function openAddStudent(){
document.location.href = 'http://localhost:8080/addStudent.html'
}

async function logAccommodation(studentId){
    let bodyObj = {
        studentId: studentId,
    }
    //make object and pass 2 IDs in
    //pass in param and body
    //param is student id, and body is the info on which accommodation to edit
    const response = await fetch(`http://localhost:8080/api/student/accomsReceived/${studentId}`, {
        method: "PUT",
        headers: {
            'Content-type': 'application/json'
        }
    })
        // .then(() => getAllStudents())
        .catch(err => console.error(err))
}

async function deleteStudent(studentId){
    await fetch(`http://localhost:8080/api/student/${studentId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))


}

async function deleteCourse(courseId){
    await fetch(`http://localhost:8080/api/course/${courseId}`, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))
}

async function getAllStudents(){

    await fetch(`http://localhost:8080/api/student`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
                data.forEach(elem => {
                console.log(elem.studentId)
                    console.log(elem.studentName)
                    console.log(elem.studentAccommodationList[0].accommodationFrequency)

                let tableRow = document.createElement("tr")
                tableRow.innerHTML = `<th scope = "row" class = ${elem.studentId}>${elem.studentId}</th>
                                 <td>${elem.studentName}</td>
                                 <td>${elem.studentAccommodationList[0].accommodationName}</td>
                                  <td id = "${elem.accommodationReceived}received">${elem.studentAccommodationList[0].accommodationReceived}</td>

                                 <td id = "${elem.studentId}frequency">${elem.studentAccommodationList[0].accommodationFrequency}</td>
                                 <td>${+elem.studentAccommodationList[0].accommodationReceived} / ${+elem.studentAccommodationList[0].accommodationFrequency}</td>
                                 <td onClick = "logAccommodation(${elem.studentAccommodationList[0].studentAccommodationId})"><button type="button" class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
            </svg></button></td>
                                 
                                 <td onClick = "deleteStudent(${elem.studentId})"><button type="button" class="btn btn-outline-danger"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
            </svg></button></td>
                                        `

                    studentTableBody.appendChild(tableRow);
        })
        .catch(err => console.error(err))

})
}

// function openRosterModal(courseId){
//     // getCourseRoster(courseId);
//     // rosterModal.style.display = "block";
//
// }

function closeRosterModal(){
    rosterModal.style.display = "none"
}

async function getCourseRoster(courseId) {
    rosterModal.style.display = "block";

    await fetch(`http://localhost:8080/api/course/${courseId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => {
            data.forEach(elem => {
                courseRosterh4.textContent = elem.courseName
            let newRow = document.createElement('tr')
            newRow.innerHTML = `<th scope = "row" class = ${elem.studentId}>${elem.studentId}</th>
                                 <td>${elem.studentName}</td>
                                 <td>${elem.studentAccommodationList[0].accommodationName}</td>
                                  <td id = "${elem.accommodationReceived}">${elem.studentAccommodationList[0].accommodationReceived}</td>
                                  <td>${elem.studentAccommodationList[0].accommodationFrequency}</td>
                                  <td>${elem.studentAccommodationList[0].coursesElapsed}</td> `

            rosterTable.appendChild(newRow)

        })
        .catch(err => console.error(err))
})
}

async function addCourse(obj){
    let courseObj = {
        courseName: courseNameInput.value,
        teacherName: courseTeacherInput.value,
        numberOfCoursesElapsed: courseElapsedInput.value
    }

    const response = await fetch(`http://localhost:8080/api/course/1`, {
        method: "POST",
        body: JSON.stringify(courseObj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if(response.status == 200){
        getTeacherCourses();
        closeAddCourseModal();

    }
}

addStudentButton.addEventListener("click", openAddStudent)
addCourseButton.addEventListener('click', openAddCourseModal)
addCourseModalButton.addEventListener('click', addCourse)
closeRosterModalBtn.addEventListener('click', closeRosterModal)