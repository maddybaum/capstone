
const registerUser = document.getElementById('username')
const registerPass = document.getElementById('password')
const registerForm = document.getElementById("registerForm")


const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = "http://local:8080/api/teachers"

const handleRegisterSubmit = async (e) => {
    e.preventDefault()

    let bodyObj = {
        username: registerUser.value,
        password: registerPass.value
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
registerForm.addEventListener("submit", handleRegisterSubmit)