
const loginUser = document.getElementById('username')
const loginPass = document.getElementById('password')
const loginButton = document.getElementById('submit')
const form = document.getElementById("loginForm")


const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = "http://local:8080/api/teachers"


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

form.addEventListener("submit", handleLoginSubmit)