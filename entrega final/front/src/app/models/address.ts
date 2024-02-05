import { State } from "./state";

export interface Address{
    "addrId": number,
    "state": State,
    "cityName": string,
    "addrPostcode": string,
    "addrStreet": string,
    "addrNumber": number,
    "addrFloor": number,
    "addrApartment": string
}
