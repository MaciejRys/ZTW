// JavaScript source code
const axios = require("axios");
var usersList = [];
var todosList = [];
async function getRestUsersList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/users")
        usersList = users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }))
    } catch (error) {
        throw error
    }
} 

async function getRestTodosList() {
    try {
        const todos = await axios.get("https://jsonplaceholder.typicode.com/todos")
        todosList = todos.data.map(({ userId, id, title, completed }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        }))
    } catch (error) {
        throw error
    }
} 
getRestUsersList();
getRestTodosList();



const { GraphQLServer } = require('graphql-yoga');
const resolvers = {
    Query: {
        users: () => usersList,
        todos: () => todosList,
        todo: (parent, args, context, info) => todoById(parent, args, context, info),
        user: (parent, args, context, info) => userById(parent, args, context, info), 
    },
    User: {
        todos: (parent, args, context, info) => {
            return todosList.filter(t => t.user_id == parent.id);
        }
    },
    ToDoItem: {
        user: (parent, args, context, info) => {
            return usersList.find(u => u.id == parent.user_id);
        }
    }, 

}

function todoById(parent, args, context, info) {
    return todosList.find(t => t.id == args.id);
}
function userById(parent, args, context, info) {
    return usersList.find(u => u.id == args.id);
} 

const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});

server.start(() => console.log(`Server is running on http://localhost:4000`)); 