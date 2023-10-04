import { VaccinationCenter } from "../Vaccination/vaccination-center";

export interface User{
    id:number;
    nom: string;
    prenom: string;
    password: string;
    role: number;
    mail: string;
    center: VaccinationCenter;
}