
const loginUser = document.getElementById('username')
const loginPass = document.getElementById('password')
const loginButton = document.getElementById("loginSubmit")


const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = "http://localhost:8080/api/teachers"


const handleLoginSubmit = async (e) => {
    e.preventDefault()

    let bodyObj = {
        teacherName: loginUser.value,
        teacherPassword: loginPass.value
    }

    console.log(bodyObj);

    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })

        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if (response.status === 200){
        document.cookie = `teacherId=${responseArr[1]}`

        if (responseArr[0] == "Username or password incorrect"){
            alert("Username or password incorrect ")
        }else{
            window.location.replace(responseArr[0])
        }
        window.loginPass.replace(responseArr[0])
    }
}

loginButton.addEventListener("click", handleLoginSubmit)