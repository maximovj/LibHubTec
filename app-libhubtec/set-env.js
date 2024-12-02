require('dotenv').config();
const fs  = require('fs');
const path  = require('path');

let environments = {};
Object
  .keys(process.env)
  .filter( (item)  => item.startsWith('ENV'))
  .forEach(( _env ) => {
    environments = {
      ...environments,
      [_env] : process.env[_env],
    }
});

const name = 'environments';
const dir = path.join(__dirname, 'src', name);
const file = path.join(__dirname, 'src', name, `${name}.ts`);

fs.mkdirSync(dir, { recursive: true });
fs.writeFileSync(file, `export const ${name} = ${ JSON.stringify(environments, null, 2)}`);
