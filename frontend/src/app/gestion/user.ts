import { VaccinationCenter } from "../Vaccination/vaccination-center";

export interface User{
    idUser:number;
    nom: string;
    prenom: string;
    password: string;
    role: {
        authority: string
    };
    mail: string;
    center: VaccinationCenter;
}