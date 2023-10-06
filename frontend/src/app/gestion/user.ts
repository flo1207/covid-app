import { VaccinationCenter } from "../Vaccination/vaccination-center";

export interface User{
    idUser:number;
    nom: string;
    prenom: string;
    password: string;
    role: number;
    mail: string;
    center: VaccinationCenter;
}