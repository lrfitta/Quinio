import { Graph } from "./graph";
import { Report } from "./report";

export interface ResponseReport {
    result : string,
    description : string,
    codeError : string,
    data : Report[],
    numberOfPages : number,
    weekly : Graph[],
    daily : Graph[]
}