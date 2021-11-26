import { Link } from "react-router-dom"


const Navigation = () => {
    return (
        <div className="navigation" >
            <Link to="/">Dashboard</Link>
            <Link to="/login">Login</Link>
        </div>
    )
}

export default Navigation
