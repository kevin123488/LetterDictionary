import axios from "axios";
import { API_BASE_URL, AI_BASE_URL } from "@/config";

function apiInstance() {
    const instance = axios.create({
        baseURL: API_BASE_URL,
        headers: {
            "Content-type": "application/json",
        }
    });
    return instance;
}

function aiInstance() {
    const instance = axios.create({
        baseURL: AI_BASE_URL,
        headers: {
            "Content-type": "application/json",
        },
        // defaults.xsrfCookieName : 'csrftoken',
        // defaults.xsrfHeaderName : 'X-CSRFToken',
    });
    return instance;
}

export { apiInstance, aiInstance }