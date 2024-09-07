import http from "k6/http";

export let options = {
  duration: "30m", // The test will run for 30 seconds
  vus: 30, // Number of virtual users
};

const port = __ENV.port;
const apiName = __ENV.apiName;

const query = `{
           test {
              id
              name
            pageCount
    author {
      id
      firstName
      lastName
    }
           }
        }`;

const payload = JSON.stringify({
  query: query, // GraphQL query
});

const headers = {
  "Content-Type": "application/json",
};

let maxRequestTime = 0;
let avgRequestTime = 0;
let requestCount = 0;

export default function () {
  const startTime = new Date().getTime();

  http.post(`http://localhost:${port}/graphql`, payload, { headers: headers });

  const requestTime = new Date().getTime() - startTime;

  avgRequestTime =
    (avgRequestTime * requestCount + requestTime) / (requestCount + 1);
  requestCount++;
  maxRequestTime = requestTime > maxRequestTime ? requestTime : maxRequestTime;


  console.log(`***************${apiName} - ${__VU}***************`);
  console.log(`avg: ${avgRequestTime}`);
  console.log(`max: ${maxRequestTime}`);
}
