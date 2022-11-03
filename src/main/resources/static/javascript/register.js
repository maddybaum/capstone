console.log("jsconnected")
const registerUser = document.getElementById('username')
const registerPass = document.getElementById('password')
const registerForm = document.getElementById("registrationForm")
const button = document.getElementById("submit")



const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = "http://localhost:8080/api/teachers"

const handleRegisterSubmit = async (e) => {

    e.preventDefault()

    let bodyObj = {
        teacherName: registerUser.value,
        teacherPassword: registerPass.value
    }
    console.log(bodyObj);


    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.err(err.message))

    const responseArr = await response.json()

    if(response.status === 200){
        window.location.replace(responseArr[0])
    }
}
button.addEventListener("click", handleRegisterSubmit)