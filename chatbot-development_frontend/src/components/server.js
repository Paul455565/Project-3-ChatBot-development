import express from 'express';
import axios from 'axios';
import dotenv from 'dotenv';
import cors from 'cors';

dotenv.config();

const app = express();
const PORT = 5000;  // Changed to 5000

app.use(cors());
app.use(express.json());

app.post('/api/chat', async (req, res) => {
    const { message } = req.body;

    try {
        const response = await axios.post(
            'https://api.openai.com/v1/completions',
            {
                model: 'gpt-4o-mini',
                prompt: message,
                max_tokens: 150,
                temperature: 0.7,
            },
            {
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${process.env.OPENAI_API_KEY}`,
                },
            }
        );

        res.json({ reply: response.data.choices[0].text.trim() });
    } catch (error) {
        console.error('Error fetching response from OpenAI:', error);
        res.status(500).json({ error: 'Error communicating with OpenAI API' });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
