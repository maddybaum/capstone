//DOM Elements

let addStudentForm = document.getElementById("addStudentForm");
let courseContainer = document.getElementById("courseContainer");
let submitButton = document.getElementById("addStudent");
let getCoursesButton = document.getElementById("getCourses")
let list = document.getElementById("studentCourseSelection")
const checkedAccommodations = document.querySelectorAll('input[name = "accommodations"]:checked').value;

//input elements
const studentName = document.getElementById("addStudentName");
const accommodationFrequency = document.getElementById("addStudentAccommodationFrequency")




const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/student"

window.onload = function(){
    getCourses();

}


const handleSubmit = async (e) => {
    e.preventDefault()

    let bodyObj= {

    }
}




//console log works
async function addStudent(obj) {
    //console.log("button clicked")

    const accommodations = document.getElementsByName('accommodations');
    let selectedAccommodations = [];

    for (let i = 0; i < accommodations.length; i++) {
        if (accommodations[i].checked) {
            selectedAccommodations.push(+accommodations[i].id)
            //console.log(accommodations[i].checked)
        }
    }

    // const courses = document.getElementsByName('course');
    let selectedCourses = [];

    for(let i = 0; i < list.rows.length; i++){
        if(list.rows.item(i).cells.item(0).firstElementChild.checked){
            let courseNames = list.rows.item(i).cells.item(0).firstElementChild.value
            selectedCourses.push(courseNames)
            console.log(list.rows.item(i).cells.item(0).firstElementChild.value)
        }
    }
    for(let i = 0; i < selectedAccommodations.length; i++) {
        let studentObj = {
            studentName: studentName.value,
            studentAccommodationList: [
                {
                    accommodationId: selectedAccommodations[i],
                    accommodationFrequency: 1,
                    accommodationReceived: 0,
                }
            ],
            //turn into radio button

            studentCourse: [
                {
                    courseId: 15,
                    // courseName: "Test2",
                    // numberOfCoursesElapsed: 5,

                }
            ]

        }

        const response = await fetch(`http://localhost:8080/api/student/newStudent`, {
            method: "POST",
            //do I put body here?
            //Need to add selected courses and accommodations
            body: JSON.stringify(studentObj),
            headers: headers
        })
            .catch(err => console.error(err.message))
        if (response.status == 200) {
            //return getStudents(teacherId);
        }
    }
    document.location.href = 'http://localhost:8080/dashboard.html'
}

//Use this method to populate the dashboard with students
//console log works
async function getCourses(){
    await fetch(`http://localhost:8080/api/course`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => {
            data.forEach(elem => {
                let listItem = document.createElement("tr")
                listItem.innerHTML = `<td><input class="form-check-input form-check-inline courseOptions" name = "course" type="checkbox" id= "id-${elem.courseId}" value="${elem.courseName}">
        <label class="form-check-label" for="coursesSelection">${elem.courseName}</label></td>`
                list.appendChild(listItem)
            })
        })
        .catch(err => console.error)
}

//function to get the accommodations selected
//query selector all to grab all of the student courses and all student accommodations
//use for loop, to go through array and check if checked attribute is checked
//


function coursesSelected(){
    const courses = document.
    selectedCourses = [];

    for(let i = 0; i < courses.length; i++){
        if(courses[i].checked){
            selectedCourses.push(courses[i])
        }
    }
}

function accommodationsSelected(){
    const accommodations = document.getElementsByName('accommodations');
    let selectedAccommodations = [];

    for(let i = 0; i < accommodations.length; i++){
        if(accommodations[i].checked){
            selectedAccommodations.push(accommodations[i].id)
        }
    }
    console.log(selectedAccommodations);
}
let getAccommodationsButton = document.getElementById('getAccommodations')
submitButton.addEventListener("click", addStudent)
getCoursesButton.addEventListener("click", getCourses)
getAccommodationsButton.addEventListener("click", accommodationsSelected)

