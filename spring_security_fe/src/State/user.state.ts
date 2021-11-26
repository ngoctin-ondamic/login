export interface IUser {
    id : number,
    username : string,
    email : string
    roles : Array<String>
}

export const initialUser : IUser = {
    id : 0,
    username : '',
    email : '',
    roles : []
}