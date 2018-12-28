import { Cat } from "./cat";

export class Role {
    id : number
    rname : String
    status : number
    isCheck : boolean
    isOfUser:boolean = false;
    p_create : boolean = false;
    p_update : boolean = false;
    p_delete : boolean = false;
    p_approve : boolean = false;
    p_admin : boolean = false;
    catId : number
    catName : String
}