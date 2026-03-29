const express = require('express');
const http = require('http');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const cors = require('cors');
const { Server } = require('socket.io');
const Message = require('./models/Message');

dotenv.config();

const app = express();
const server = http.createServer(app);
const io = new Server(server, {
    cors: { origin: "*" }
});

app.use(cors());
app.use(express.json());

// Routes
app.use('/api/auth', require('./routes/authRoutes'));
app.use('/api/sessions', require('./routes/sessionRoutes'));
app.use('/api/admin', require('./routes/adminRoutes'));

// Socket.io for Real-time Chat and Session Updates
io.on('connection', (socket) => {
    console.log('User connected:', socket.id);

    socket.on('joinSession', async ({ sessionId }) => {
        socket.join(sessionId);
        console.log(`User joined session: ${sessionId}`);

        // Fetch and send previous messages
        const messages = await Message.find({ session: sessionId }).sort({ createdAt: 1 });
        socket.emit('loadMessages', messages);
    });

    socket.on('sendMessage', async ({ sessionId, userId, userName, text }) => {
        const newMessage = await Message.create({
            session: sessionId,
            user: userId,
            userName,
            text
        });
        io.to(sessionId).emit('message', newMessage);
    });

    socket.on('disconnect', () => {
        console.log('User disconnected');
    });
});

const PORT = process.env.PORT || 5000;

mongoose.connect(process.env.MONGO_URI)
    .then(() => {
        console.log('MongoDB connected');
        server.listen(PORT, () => console.log(`Server running on port ${PORT}`));
    })
    .catch(err => console.error(err));
