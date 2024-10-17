 //src/chatService.js

import axios, {Axios} from "axios";

 const REST_API_BASE_URL = 'http://localhost:8081/Chatbotdb/messages';

export const createMessage =(messageData) =>
    axios.post(`${REST_API_BASE_URL}/create`,messageData);
export const getMessagesByUserId =() => axios.get(`${REST_API_BASE_URL}/getMessagesByUserId`);

export const getAllMessages = () => axios.get(`${REST_API_BASE_URL}/getAll`);
