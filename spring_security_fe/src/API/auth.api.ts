import httpAuth from "./http.auth";

class AuthAPI {
  async login(username: string, password: string) {
    const params = new URLSearchParams();
    params.append("username", username);
    params.append("password", password);
    return await httpAuth.post<any>("api/login", params);
  }

  async getPrincipal(token: string) {
    return await httpAuth.get<any>("api/user/get/byToken",{
        headers : {
            'Access-Control-Allow-Origin' : '*',
            'Access-Control-Allow-Methods': 'GET,POST,OPTIONS,DELETE,PUT',
            'Authorization' : `Bearer ${token}`
        }
    })
  }
}

export default new AuthAPI();
