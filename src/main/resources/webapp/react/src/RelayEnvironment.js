const {
  Environment,
  Network,
  RecordSource,
  Store,
} = require('relay-runtime');

const store = new Store(new RecordSource());

async function fetchGraphQL(params, variables) {

  const response = await fetch('http://localhost:3000/graphql', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      query: params.text,
      variables,
    }),
  });
  // Get the response as JSON
  return await response.json();
}

export default new Environment({
  network: Network.create(fetchGraphQL),
  store,
});