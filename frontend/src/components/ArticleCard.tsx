import React from "react";
import {Link} from "react-router-dom";

interface ArticleCardProps {
  id: string;
  title: string;
  content: string;
  imageUrl?: string;
}

const ArticleCard: React.FC<ArticleCardProps> = ({ id, title, content, imageUrl }) => {
  return (
    <Link to={`/articles/${id}`} className="flex p-4 border border-gray-200 rounded-lg shadow bg-white">
      {imageUrl && (
        <div className="flex-shrink-0 lg:w-2/9 md:3/9 w-4/9 pr-4">
          <img
            src={imageUrl}
            alt={title}
            className="w-full h-30 sm:h-36 md:h-48 object-fill rounded-lg"
          />
        </div>
      )}
      <div className="flex-grow">
        <h2 className="lg:text-xl text-base font-semibold text-gray-800 mb-2">{title}</h2>
        <p className="text-gray-600 md:text-sm hidden md:line-clamp-3">
          {content}
        </p>
      </div>
    </Link>
  );
};

export default ArticleCard;