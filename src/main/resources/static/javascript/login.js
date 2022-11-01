const registerDiv = document.getElementById('sign-up-htm')
const registerUser = document.getElementById("signUpUser")
const registerPass = document.getElementById('signUpPass')
const registerConf = document.getElementById('repeatPass')
const loginUser = document.getElementById('loginUser')
const loginPass = document.getElementById('loginPass')
const loginButton = document.getElementById('loginButton')
const registerButton = document.getElementById('registerButton')
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
registerButton.addEventListener("click", handleRegisterSubmit)

const handleLoginSubmit = async (e) => {
    e.preventDefault()

    let bodyObj = {
        username: loginUser.value,
        password: loginPass.value
    }

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })

        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        document.cookie = `userId=${responseArr[1]}`
        window.loginPass.replace(responseArr[0])
    }
}

loginButton.addEventListener("click", handleLoginSubmit)