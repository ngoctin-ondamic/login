import React,{useState,useEffect} from 'react'
import { useNavigate } from 'react-router';
import { IUser } from '../State/user.state'

const AdminPage = () => {
    const [isAuthorized,setIsAuthorized] = useState<string|null>(localStorage.getItem('authorization'));
    const navigation = useNavigate();
    useEffect(() => {
        if(isAuthorized){

        }else{
            navigation("/login")
        }
    }, [])

    const handleOnClick = (event : React.MouseEvent) => {
        if(event.currentTarget.getAttribute('name') ==='logout'){
            localStorage.removeItem('user');
            localStorage.removeItem('access_token');
            localStorage.removeItem('authorization');
            navigation('/login')
        }
    }

    return (
        <div className="admin" >
            <h2>This is Admin</h2>
            <button onClick={handleOnClick} name="logout" >Log Out</button>
        </div>
    )
}

export default AdminPage
