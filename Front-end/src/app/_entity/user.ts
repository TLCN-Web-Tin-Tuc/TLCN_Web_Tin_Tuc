import { Role } from "./role";

export class User {
    id : number
    email : string;
    password : string
    confirmPassword : string
    status : number
    firstName : string
    lastName : string
    dateOfBirth : Date
    dateTemp : string
    sex : number    
    avatar : string
    address : string
    phone :  string 
    dateCreated : Date 
    userCreated : string
    dateUpdated : Date 
    userUpdated : string
    roles: Role[];
}