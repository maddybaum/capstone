//DOM Elements

let addStudentForm = document.getElementById("#addStudentForm");
let courseContainer = document.getElementById("#courseContainer");
let submitButton = document.getElementById("#addStudent");


const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/student"

const handleSubmit = async (e) => {
    e.preventDefault()

    let bodyObj= {

    }
}

async function getAllCourses(){
    await fetch(`${baseUrl}`, {
        method: "GET",
        headers: headers
    })

        .then(response => response.json())
        .then(data => createRadioOptions(data))
        .catch(err => console.error(err))
}

const createRadioOptions = (array) => {
    courseContainer = ``
    array.forEach(obj => {
        let label = document.createElement("label");
        label.innerText = obj;
        let input = document.createElement("input");
        input.type = "radio"
        input.classList.add("selectedCourse")
        label.appendChild(input);
        courseContainer.appendChild(label);
    })
}

async function addStudent(obj){
    const response = await fetch(`http://localhost:8080/api/dashboard.html`, {
        method: "POST",
        //do I put body here?

        //Need to add selected courses and accommodations
        studentName: JSON.stringify(obj),
        headers: headers
    })

        .catch(err => console.error(err.message))
        if(response.status == 200){
        return getStudents(teacherId);
    }
}

//Use this method to populate the dashboard with students
async function getStudents(teacherId){
    await fetch(`http://localhost:8080/api/course/teacher/${teacherId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createStudentInTable(data))
        .catch(err => console.error)
}

//function to get the accommodations selected
function accommodationsSelected(){
    const accoms = document.getElementsByClassName("form-check-input")
    selectedAccoms = [];

    for(let i = 0; i < accoms.length; i++){
        if(accoms[i].checked){
            selectedAccoms.push(accoms[i])
        }
    }
}

function coursesSelected(){
    const courses = document.getElementsByClassName('selectedCourse')
    selectedCourses = [];

    for(let i = 0; i < selectedCourses.length; i++){
        if(selectedCourses[i].checked){
            selectedCourses.push(selectedCourses[i])
        }
    }
}
//Probably don't need this here
// async function handleDelete(studentId){
//     await fetch(`${baseUrl}/studentId`,{
//         method: "DELETE",
//         headers: headers
//     })
//         .catch(err => console.error(err))
//
//     return getStudents(teacherId)
// }