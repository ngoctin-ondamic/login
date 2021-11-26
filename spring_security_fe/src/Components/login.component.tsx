import React, {useState} from 'react'
import { useNavigate } from 'react-router';
import authService from '../Service/auth.service';

const LoginPage = () => {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const navigate = useNavigate();
    const handleOnChange = (event : React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === 'username'){
            setUsername(event.target.value);
        }else{
            setPassword(event.target.value)
        }
    }
    const handleOnClick = (event : React.MouseEvent) => {
        if(event.currentTarget.getAttribute('name') === 'login'){
            // useNavigate 
            authService.login(username,password)
            .then(response => {
                if(response.id !== 0){
                    if(response.roles.includes('ADMIN')){
                        navigate(`/admin/${response.username}`)
                    }else{
                        navigate(`/member/${response.username}`)
                    }
                }
            })
            .catch(reason =>  {
                console.log(reason);
                alert("Login Failed !")
            })
        }
        setUsername("");
        setPassword("");
    }
    return (
        <div className="login" >
            Username : <input type="text" name="username" onChange={handleOnChange} value={username}/>
            Password : <input type="password" name="password" onChange={handleOnChange} value={password} />
            <button type="submit" name="login" onClick={handleOnClick}>Login</button>
        </div>
    )
}

export default LoginPage
