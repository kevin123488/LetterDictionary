// ai변환 보내는 요청

import { aiInstance } from "./index.js";
const ai = aiInstance();


async function sendTransform(transformInfo, success, fail) {
  // 로그인
  console.log('================================================================')
  console.log(transformInfo.image.image)
  await ai.post(`/ai/yeopseo/${transformInfo.filterCode}`, transformInfo.image.image).then(success).catch(fail);
}


export { sendTransform, };
