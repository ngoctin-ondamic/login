import authApi from "../API/auth.api";
import { initialUser, IUser } from "../State/user.state";
class AuthService {
  async login(username: string, password: string) {
    const response = await authApi.login(username, password);
    console.log(localStorage.getItem('access_token'))
    const access_token = String(response.data.access_token);
    var user : IUser = initialUser;
    console.log(response)
    if (access_token) {
      const userResponse = await authApi.getPrincipal(access_token);
      if (userResponse) {
        const roles: Array<String> = userResponse.data.roles.map((role: any) =>
          String(role.name)
        );
        user = {
          id: userResponse.data.id,
          username: userResponse.data.username,
          email: userResponse.data.email,
          roles: roles,
        };
        localStorage.setItem('authorization','true');
      }
    }
    return user;
  }
}
export default new AuthService();
